package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONObject;

import eus.ehu.tta.viajelp.presentation.AdapterSituationsGridView;
import eus.ehu.tta.viajelp.presentation.ProgressTask;
import eus.ehu.tta.viajelp.model.comms.RestClient;

public class SituationsActivity extends AppCompatActivity {

    GridView gridView;
    private RestClient restClient;
    private String listaFrasesJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situations);

        restClient = new RestClient();
        pedirFrases();

        gridView = (GridView) findViewById(R.id.gridViewSituations);
        /*gridView.setAdapter(new AdapterSituationsGridView(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SituationsActivity.this,SituationActivity.class);
                startActivity(intent);
            }
        });*/
    }

    public void pedirFrases(){
        new ProgressTask<String>(this) {
            @Override
            protected String work() throws Exception {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tipo","S");
                return restClient.postJsonConnection(jsonObject,restClient.LISTA_FRASES_URL);
            }

            @Override
            protected void onFinish(String result) {
                listaFrasesJson = result;//Se recibe el string JSON de las frases
                loadNextActivity();
            }
        }.execute();
    }

    public void loadNextActivity(){
        gridView = (GridView) findViewById(R.id.gridViewSituations);
        gridView.setAdapter(new AdapterSituationsGridView(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SituationsActivity.this,SituationActivity.class);
                intent.putExtra("frases",listaFrasesJson);
                intent.putExtra("situacion",chooseSituation(position));
                startActivity(intent);
            }
        });
    }

    public String chooseSituation(int position){
        String situation = "";

        switch (position){
            case 0:
                situation = "AEROPUERTO";
                break;
            case 1:
                situation = "HOTEL";
                break;
            case 2:
                situation = "BAR- SOCIALIZACIÓN-FIESTA";
                break;
            case 3:
                situation = "COMIDA/SUPERMERCADO";
                break;
            case 4:
                situation = "MÉDICO- SALUD";
                break;
            case 5:
                situation = "UBICACIÓN";
                break;
            case 6:
                situation = "TRANSPORTE PÚBLICO";
                break;
            case 7:
                situation = "ESTUDIOS";
                break;
        }
        return situation;
    }
}
