package eus.ehu.tta.viajelp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.presentation.AdapterFraseListView;
import eus.ehu.tta.viajelp.presentation.view.DialogPlayer;

public class ForoActivity extends AppCompatActivity {

    private List<Frase> listaFrases;
    private JSONTools jsonTools;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        fragmentManager = getSupportFragmentManager();
        jsonTools = new JSONTools();

        ListView listView = (ListView) findViewById(R.id.lvFrasesForo);
        listaFrases = jsonTools.getFrasesFromJson(getIntent().getStringExtra("frasesForo"));

        listView.setAdapter(new AdapterFraseListView(this,fragmentManager,listaFrases,"foro"));//Se pasa las frases al adaptador

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
