package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONObject;

import eus.ehu.tta.viajelp.model.LogicaDB;
import eus.ehu.tta.viajelp.model.Usuario;
import prof.comms.ProgressTask;
import prof.comms.RestClient;

public class AprendemeActivity extends AppCompatActivity {

    private LogicaDB logicaDB;
    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendeme);
        logicaDB = new LogicaDB(this,"VIAJELPDB",null,1);
        restClient = new RestClient();
    }
    public void clickAgregarPalabra(View view){
        Intent intent = new Intent(this, AgregarPalabrasActivity.class);
        intent.putExtra("idUsuario",getIntent().getIntExtra("idUsuario",0));
        startActivity(intent);
    }

    public void clickPalabras(View view){
        getPalabrasFromServer();
    }

    public void getPalabrasFromServer(){
        new ProgressTask<String>(this) {
            @Override
            protected String work() throws Exception {
                JSONObject jsonObject = new JSONObject();
                Usuario usuario = logicaDB.getUsuarioFromDB(getIntent().getIntExtra("idUsuario",0));

                jsonObject.put("idUsuario",usuario.getIdUsuario());
                return restClient.postJsonConnection(jsonObject,restClient.LISTA_PALABRAS_URL);
            }

            @Override
            protected void onFinish(String result) {
                //Se recibe el string JSON de las frases
                goToPalabrasActivity(result);
            }
        }.execute();
    }

    public void goToPalabrasActivity(String palabras){
        Intent intent = new Intent(this, PalabrasActivity.class);
        intent.putExtra("palabras",palabras);
        intent.putExtra("idUsuario",getIntent().getIntExtra("idUsuario",0));
        startActivity(intent);
    }
}
