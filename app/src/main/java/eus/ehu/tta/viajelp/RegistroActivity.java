package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import eus.ehu.tta.viajelp.model.Business;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.LogicaDB;
import eus.ehu.tta.viajelp.model.Usuario;
import eus.ehu.tta.viajelp.model.comms.ProgressTask;
import eus.ehu.tta.viajelp.model.comms.RestClient;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre,etUsuario,etEdad,etPassword1,etEtPassword2;
    private Button btnResgitro;
    private LogicaDB logicaDB;
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText)findViewById(R.id.etNombreRegistro);
        etUsuario = (EditText)findViewById(R.id.etUsuarioRegistro);
        etEdad = (EditText)findViewById(R.id.etEdadRegistro);
        etPassword1 = (EditText)findViewById(R.id.etPasswordRegistro);
        etEtPassword2 = (EditText)findViewById(R.id.etPasswordRegistroBis);
        btnResgitro = (Button) findViewById(R.id.btnRegistro);


    }

    public void clickButtonRegistro(View view){
        if(etUsuario.getText().toString().isEmpty())
            Toast.makeText(this,R.string.usuarioRequerido,Toast.LENGTH_SHORT).show();
        if(etPassword1.getText().toString().isEmpty())
            Toast.makeText(this,R.string.passwordRequerido,Toast.LENGTH_SHORT).show();
        if(etEdad.getText().toString().isEmpty())
            Toast.makeText(this,R.string.rellenaTodosLosCampos,Toast.LENGTH_SHORT).show();
        if(etPassword1.getText().toString().equals(etEtPassword2.getText().toString())){
            logicaDB = new LogicaDB(this,"VIAJELPDB",null,1);
            iniciarRegistro();
        }else{
            Toast.makeText(this,R.string.passwordDebeCoicidir,Toast.LENGTH_SHORT).show();
        }

    }

    public void iniciarRegistro(){
        new ProgressTask<String>(this) {
            @Override
            protected String work() throws Exception {
                Usuario usuario = new Usuario();
                usuario.setNombre(etNombre.getText().toString());
                usuario.setEdad(Integer.parseInt(etEdad.getText().toString()));
                usuario.setPassword(etPassword1.getText().toString());
                usuario.setUsuario(etUsuario.getText().toString());

                RestClient restClient = new RestClient();
                JSONTools jsonTools = new JSONTools();
                JSONObject jsonObject = jsonTools.getJsonFromUsuario(usuario);

                String resp = restClient.postJsonConnection(jsonObject,restClient.REGISTRO_URL);
                Business business = new Business();
                usuario = business.loginPost(usuario.getUsuario(),usuario.getPassword());
                idUsuario = usuario.getIdUsuario();
                logicaDB.crearTablaUsuario();
                logicaDB.saveUsuario(usuario);


                return resp;
            }

            @Override
            protected void onFinish(String result) {
                //Se recibe el string JSON de las frases
                goToMainActivity(result);
            }
        }.execute();
    }

    public void goToMainActivity(String result){
        if(result.equals("200OK")){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("idUsuario",idUsuario);
            startActivity(intent);
            finish();
        }else
            Toast.makeText(this,R.string.usuarioYaexiste,Toast.LENGTH_SHORT).show();

    }

}
