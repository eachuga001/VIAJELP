package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.viajelp.model.beans.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;

public class SituationActivity extends AppCompatActivity {

    JSONTools jsonTools;
    List<Frase> listaFrases;
    EditText buscador;
    private List<Frase> array_sort = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        jsonTools = new JSONTools();
        Intent intent = getIntent();

        listaFrases = jsonTools.getFrasesFromJson(intent.getStringExtra("frases"));
        listaFrases = jsonTools.getFrasesBySituacion(intent.getStringExtra("situacion"),listaFrases);

        buscador = (EditText) findViewById(R.id.etBuscador);
        final ListView listView = (ListView) findViewById(R.id.lvFrases);
        listView.setAdapter(new AdapterFraseListView(this,listaFrases,"situaciones"));
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
                listView.setAdapter(new AdapterFraseListView(SituationActivity.this,array_sort,"situaciones"));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Aqui se pondria el titulo de cada situacion que se puede obtener de la frase a mostrar
        setTitle(intent.getStringExtra("situacion"));

        //de momento no hace falta que ocurra nada cuando toca un elemento de la lista
    }



}
