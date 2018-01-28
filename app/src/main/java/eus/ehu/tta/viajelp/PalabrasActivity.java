package eus.ehu.tta.viajelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.Palabra;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;

public class PalabrasActivity extends AppCompatActivity {

    JSONTools jsonTools;
    List<Palabra> listaPalabras;
    EditText buscador;
    List<Palabra> array_sort = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabras);

        jsonTools = new JSONTools();
        listaPalabras = jsonTools.getPalabrasFromJson(getIntent().getStringExtra("palabras"));

        buscador = (EditText)findViewById(R.id.etBuscadorPalabras);
        listView = (ListView) findViewById(R.id.lvPalabras);
        listView.setAdapter(new AdapterFraseListView(this,listaPalabras,"palabras"));
        //CODIGO PARA FILTRAR LAS PALABRAS EN EL LIST VIEW
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textLength = buscador.getText().length();
                array_sort.clear();
                for(int i=0;i<listaPalabras.size();i++){
                    if(textLength <= listaPalabras.get(i).getPalabraEsp().length()){
                        if(listaPalabras.get(i).getPalabraEsp().toLowerCase().contains(buscador.getText().toString())
                                || listaPalabras.get(i).getPalabraEng().toLowerCase().contains(buscador.getText().toString()))
                            array_sort.add(listaPalabras.get(i));
                    }

                }
                listView.setAdapter(new AdapterFraseListView(PalabrasActivity.this,array_sort,"palabras"));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
