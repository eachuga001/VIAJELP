package eus.ehu.tta.viajelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.Palabra;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;

public class PalabrasActivity extends AppCompatActivity {

    JSONTools jsonTools;
    List<Palabra> listaPalabras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palabras);

        jsonTools = new JSONTools();
        listaPalabras = jsonTools.getPalabrasFromJson(getIntent().getStringExtra("palabras"));
        ListView listView = (ListView) findViewById(R.id.lvPalabras);
        listView.setAdapter(new AdapterFraseListView(this,listaPalabras,"palabras"));

    }
}
