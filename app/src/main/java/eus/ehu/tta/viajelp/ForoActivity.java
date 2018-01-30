package eus.ehu.tta.viajelp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.viajelp.model.beans.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;
import eus.ehu.tta.viajelp.presentation.view.DialogPlayer;

public class ForoActivity extends AppCompatActivity {

    private List<Frase> listaFrases;
    private JSONTools jsonTools;
    private FragmentManager fragmentManager;
    private ListView listView;
    private EditText buscador;
    List<Frase> array_sort = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        fragmentManager = getSupportFragmentManager();
        jsonTools = new JSONTools();

        listView = (ListView) findViewById(R.id.lvFrasesForo);
        listaFrases = jsonTools.getFrasesFromJson(getIntent().getStringExtra("frasesForo"));
        buscador = (EditText)findViewById(R.id.etBuscadorForo);
        listView.setAdapter(new AdapterFraseListView(this,fragmentManager,listaFrases,"foro"));//Se pasa las frases al adaptador
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idUser = getIntent().getIntExtra("idUsuario",0);
                DialogPlayer dp =  DialogPlayer.newInstance(ForoActivity.this,listaFrases.get(position),idUser,position,"dialogoResponder");
                dp.show(fragmentManager,"tag");

            }
        });

        //CODIGO PARA FILTRAR LAS PALABRAS EN EL LIST VIEW
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textLength = buscador.getText().length();
                array_sort.clear();
                for(int i=0;i<listaFrases.size();i++){
                    if(textLength <= listaFrases.get(i).getFraseEsp().length()){
                        if(listaFrases.get(i).getFraseEsp().toLowerCase().contains(buscador.getText().toString())
                                || listaFrases.get(i).getFraseEng().toLowerCase().contains(buscador.getText().toString()))
                            array_sort.add(listaFrases.get(i));
                    }

                }
                listView.setAdapter(new AdapterFraseListView(ForoActivity.this,array_sort,"foro"));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addFraseForo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //Para crear un dialogo se le pasa el id del usuario y el tipo de dialogo mediante un string
                DialogPlayer dp = DialogPlayer.newInstance(getIntent().getIntExtra("idUsuario",0),"dialogoPreguntar");
                dp.show(fragmentManager,"Tag");
            }
        });
    }

}
