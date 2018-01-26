package eus.ehu.tta.viajelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.Palabra;
import prof.comms.ProgressTask;
import prof.comms.RestClient;

public class AgregarPalabrasActivity extends AppCompatActivity {

    private EditText etPalabra,etTraduccion;
    private Button btnGuardarPalabra;
    private JSONTools jsonTools;
    RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_palabras);

        restClient = new RestClient();
        jsonTools = new JSONTools();
        etPalabra = (EditText)findViewById(R.id.etPalabra);
        etTraduccion = (EditText)findViewById(R.id.etTraduccionPalabra);
        btnGuardarPalabra = (Button)findViewById(R.id.btnGuardarPalabra);

    }

    public void clickGuardarPalabra(View view){
        loadPalabraToServer();
    }

    public void loadPalabraToServer() {
        new ProgressTask<String>(this) {
            @Override
            protected String work() throws Exception {
                String palabra = etPalabra.getText().toString();
                String traduccion = etTraduccion.getText().toString();
                int id = getIntent().getIntExtra("idUsuario", 0);

                JSONObject jsonObject = jsonTools.getPalabraJSON(palabra, traduccion, id);

                return restClient.postJsonConnectionUp(jsonObject, restClient.UP_PALABRA_URL);
            }

            @Override
            protected void onFinish(String result) {
                //Se recibe la respuesta de la conexion
                endActivity(result);
            }
        }.execute();

    }

    public void endActivity(String response){
        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
        finish();
    }
}
