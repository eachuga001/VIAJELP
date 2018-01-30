package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Locale;

import eus.ehu.tta.viajelp.model.LogicaDB;
import eus.ehu.tta.viajelp.model.beans.Usuario;
import eus.ehu.tta.viajelp.presentation.ProgressTask;
import eus.ehu.tta.viajelp.model.comms.RestClient;

public class MainActivity extends AppCompatActivity {

    Button btnSituaciones,btnAprendeme,btnForo,btnIdioma;
    RestClient restClient;
    LogicaDB logicaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logicaDB = new LogicaDB(this,"VIAJELPDB",null,1);
        restClient = new RestClient();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                logicaDB.borrarTablaUsuario();
                Toast.makeText(getApplicationContext(),R.string.sesionCerrada,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //btnIdioma = (Button)findViewById(R.id.btnIdioma);
        btnSituaciones = (Button)findViewById(R.id.btnSituaciones);
        btnAprendeme = (Button)findViewById(R.id.btnAprendeme);
        btnForo = (Button)findViewById(R.id.btnForo);
        TextView tvLogin = (TextView)findViewById(R.id.tvSaludoLogin);
        Usuario u = logicaDB.getUsuarioFromDB(getIntent().getIntExtra("idUsuario",0));
        tvLogin.setText(getString(R.string.hola) + " "+u.getUsuario());

    }

    public void clickSituaciones(View view){
        Intent intent =  new Intent(this,SituationsActivity.class);
        startActivity(intent);
    }

    public void clickAprendeme(View view){
        int idUsuario = getIntent().getIntExtra("idUsuario",0);
        Intent intent =  new Intent(this,AprendemeActivity.class);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void clickForo(View view){
        pedirFrases();

    }

    public void goToForoActivity(String strigFrasesForo){
        int idUsuario = getIntent().getIntExtra("idUsuario",0);
        Intent intent =  new Intent(this,ForoActivity.class);
        intent.putExtra("frasesForo",strigFrasesForo);
        intent.putExtra("idUsuario",idUsuario);
        startActivity(intent);
    }

    public void clickIdioma(View view){
        Locale localizacion = new Locale("en", "EN");

        Locale.setDefault(localizacion);
        Configuration config = new Configuration();
        config.locale = localizacion;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void pedirFrases(){
        new ProgressTask<String>(this) {
            @Override
            protected String work() throws Exception {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tipo","F");
                return restClient.postJsonConnection(jsonObject,restClient.LISTA_FRASES_URL);
            }

            @Override
            protected void onFinish(String result) {
                //Se recibe el string JSON de las frases
                goToForoActivity(result);
            }
        }.execute();
    }



}
