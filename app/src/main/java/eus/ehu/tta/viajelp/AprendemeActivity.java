package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AprendemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprendeme);
    }
    public void clickAgregarPalabra(View view){
        Intent intent = new Intent(this, AgregarPalabrasActivity.class);
        startActivity(intent);
    }

    public void clickPalabras(View view){
        Intent intent = new Intent(this, PalabrasActivity.class);
        startActivity(intent);
    }
}
