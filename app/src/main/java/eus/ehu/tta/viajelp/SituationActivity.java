package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.List;

import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.presentation.AdapterSituationListView;
import prof.comms.ProgressTask;
import prof.comms.RestClient;

public class SituationActivity extends AppCompatActivity {

    JSONTools jsonTools;
    List<Frase> listaFrases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);
        jsonTools = new JSONTools();
        Intent intent = getIntent();

        listaFrases = jsonTools.getFrasesFromJson(intent.getStringExtra("frases"));
        listaFrases = jsonTools.getFrasesBySituacion(intent.getStringExtra("situacion"),listaFrases);

        ListView listView = (ListView) findViewById(R.id.lvFrases);
        listView.setAdapter(new AdapterSituationListView(this,listaFrases));

        //Aqui se pondria el titulo de cada situacion que se puede obtener de la frase a mostrar
        setTitle(intent.getStringExtra("situacion"));

        //de momento no hace falta que ocurra nada cuando toca un elemento de la lista
    }



}
