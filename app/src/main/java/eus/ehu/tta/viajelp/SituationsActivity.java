package eus.ehu.tta.viajelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import eus.ehu.tta.viajelp.presentation.SelectorAdapterSituation;

public class SituationsActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situations);

        gridView = (GridView) findViewById(R.id.gridViewSituations);
        gridView.setAdapter(new SelectorAdapterSituation(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(SituationsActivity.this,)
            }
        });
    }
}
