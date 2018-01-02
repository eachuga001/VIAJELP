package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSituaciones,btnAprendeme,btnForo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSituaciones = (Button)findViewById(R.id.btnSituaciones);
        btnAprendeme = (Button)findViewById(R.id.btnAprendeme);
        btnForo = (Button)findViewById(R.id.btnForo);

    }

    public void clickSituaciones(View view){
        Intent intent =  new Intent(this,SituationsActivity.class);
        startActivity(intent);
    }

    public void clickAprendeme(View view){
        Intent intent =  new Intent(this,AprendemeActivity.class);
        startActivity(intent);
    }

    public void clickForo(View view){
        Intent intent =  new Intent(this,ForoActivity.class);
        startActivity(intent);
    }

}
