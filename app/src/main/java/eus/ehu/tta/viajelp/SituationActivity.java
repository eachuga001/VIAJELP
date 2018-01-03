package eus.ehu.tta.viajelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import eus.ehu.tta.viajelp.presentation.AdapterSituationListView;

public class SituationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation);

        ListView listView = (ListView) findViewById(R.id.lvFrases);
        listView.setAdapter(new AdapterSituationListView(this));

        //Aqui se pondria el titulo de cada situacion que se puede obtener de la frase a mostrar
        setTitle("Hotel");

        //de momento no hace falta que ocurra nada cuando toca un elemento de la lista
    }
}
