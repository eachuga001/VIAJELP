package eus.ehu.tta.viajelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;

public class ForoActivity extends AppCompatActivity {

    private List<Frase> listaFrases;
    private JSONTools jsonTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        jsonTools = new JSONTools();

        ListView listView = (ListView) findViewById(R.id.lvFrasesForo);
        listaFrases = jsonTools.getFrasesFromJson(getIntent().getStringExtra("frasesForo"));

        listView.setAdapter(new AdapterFraseListView(this,listaFrases,"foro"));
    }
}
