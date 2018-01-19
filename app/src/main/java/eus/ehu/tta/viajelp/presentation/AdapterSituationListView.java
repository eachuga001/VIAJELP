package eus.ehu.tta.viajelp.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import eus.ehu.tta.viajelp.R;
import eus.ehu.tta.viajelp.model.Business;
import eus.ehu.tta.viajelp.model.Frase;

/**
 * Created by edwin on 3/01/18.
 */

public class AdapterSituationListView extends BaseAdapter{

    LayoutInflater layoutInflater;

    public static List<Frase> listaFrases;

    public AdapterSituationListView(Activity a,List<Frase> listaFrases){
        layoutInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listaFrases = listaFrases;//Esto hay que cambiar
    }

    @Override
    public int getCount() {
        return listaFrases.size();
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
        View view = convertView;
        if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_situation_item_list,null);

        TextView tvFraseEsp = view.findViewById(R.id.tvFraseEsp);
        TextView tvFraseEng = view.findViewById(R.id.tvFraseEng);


        tvFraseEsp.setText(listaFrases.get(position).getFraseEsp());
        tvFraseEng.setText(listaFrases.get(position).getFraseEng());

        return view;
    }

}
