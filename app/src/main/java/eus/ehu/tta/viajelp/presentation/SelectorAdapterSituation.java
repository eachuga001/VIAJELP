package eus.ehu.tta.viajelp.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

import eus.ehu.tta.viajelp.R;

/**
 * Created by edwin on 2/01/18.
 */

public class SelectorAdapterSituation extends BaseAdapter {
    LayoutInflater layoutInflater;
    public static Vector<String> situationVector;

    public SelectorAdapterSituation(Activity a ){
        layoutInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initVectorSituation(a);
    }

    @Override
    public int getCount() {
        return situationVector.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvNombreSituacion;
        ImageView ivSituacion;

        String nombreSituacion = situationVector.elementAt(position);

        View view = convertView;

        if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_situations_detalle,parent,false);

        tvNombreSituacion = view.findViewById(R.id.nombreSituacion);
        ivSituacion = view.findViewById(R.id.imagenSituacion);

        tvNombreSituacion.setText(nombreSituacion);

        switch (position){
            case 0:
                ivSituacion.setImageResource(R.drawable.aeropuerto);
                break;
            case 1:
                ivSituacion.setImageResource(R.drawable.hotel);
                break;
            case 2:
                ivSituacion.setImageResource(R.drawable.bar);
                break;
            case 3:
                ivSituacion.setImageResource(R.drawable.comida);
                break;
            case 4:
                ivSituacion.setImageResource(R.drawable.salud);
                break;
            case 5:
                ivSituacion.setImageResource(R.drawable.ubicacion);
                break;
            case 6:
                ivSituacion.setImageResource(R.drawable.transporte);
                break;
            case 7:
                ivSituacion.setImageResource(R.drawable.estudios);
                break;
        }


        return view;
    }

    public static void initVectorSituation(Activity a){
        situationVector = new Vector<>();

        //Esto tendria que hacerlo de otra forma
        situationVector.add(a.getResources().getString(R.string.aeropuerto));
        situationVector.add(a.getResources().getString(R.string.hotel));
        situationVector.add(a.getResources().getString(R.string.bar));
        situationVector.add(a.getResources().getString(R.string.comida));
        situationVector.add(a.getResources().getString(R.string.salud));
        situationVector.add(a.getResources().getString(R.string.ubicacion));
        situationVector.add(a.getResources().getString(R.string.transporte));
        situationVector.add(a.getResources().getString(R.string.estudios));
    }
}
