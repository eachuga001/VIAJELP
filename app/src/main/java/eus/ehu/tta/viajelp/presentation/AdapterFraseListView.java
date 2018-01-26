package eus.ehu.tta.viajelp.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import eus.ehu.tta.viajelp.R;
import eus.ehu.tta.viajelp.model.Business;
import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.Palabra;

/**
 * Created by edwin on 3/01/18.
 */

public class AdapterFraseListView extends BaseAdapter{

    LayoutInflater layoutInflater;

    public static List<Frase> listaFrases;
    public static List<Palabra> listaPalabras;
    private String type;
    int size;


    public AdapterFraseListView(Activity a, Object lista,String type){
        layoutInflater = (LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.type = type;
        if(type.equals("foro") || type.equals("situaciones")){
            this.listaFrases = (ArrayList)lista;//Esto hay que cambiar
            size = ((ArrayList) listaFrases).size();
        }else{
            this.listaPalabras = (ArrayList)lista;
            size = listaPalabras.size();
        }

    }

    @Override
    public int getCount() {
        //return listaFrases.size();
        return size;
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
        /*if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_situation_item_list,null);

        TextView tvFraseEsp = view.findViewById(R.id.tvFraseEsp);
        TextView tvFraseEng = view.findViewById(R.id.tvFraseEng);

        tvFraseEsp.setText(listaFrases.get(position).getFraseEsp());
        tvFraseEng.setText(listaFrases.get(position).getFraseEng());*/
        switch (type){
            case "situaciones":
                view = workForSituations(position,view);
                break;
            case "foro":
                view = workForForo(position,view);
                break;
            case "palabras":
                view = workForPalabras(position,view);
                break;
        }

        return view;
    }

    public View workForPalabras(int position, View convertView){
        View view = convertView;
        if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_palabra_item,null);

        TextView tvPalabraEsp = view.findViewById(R.id.tvPalabraEsp);
        TextView tvPalabraEng = view.findViewById(R.id.tvPalabraEng);


        tvPalabraEsp.setText(listaPalabras.get(position).getPalabraEsp());
        tvPalabraEng.setText(listaPalabras.get(position).getPalabraEng());

        return view;
    }

    public View workForSituations(int position, View convertView){

        View view = convertView;
        if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_situation_item_list,null);

        TextView tvFraseEsp = view.findViewById(R.id.tvFraseEsp);
        TextView tvFraseEng = view.findViewById(R.id.tvFraseEng);


        tvFraseEsp.setText(listaFrases.get(position).getFraseEsp());
        tvFraseEng.setText(listaFrases.get(position).getFraseEng());

        return view;
    }

    public View workForForo(int position, View convertView){
        View view = convertView;
        if(convertView == null)
            view = layoutInflater.inflate(R.layout.activity_foro_item,null);

        TextView tvFraseEsp = view.findViewById(R.id.tvFraseEspForo);
        TextView tvFraseEng = view.findViewById(R.id.tvFraseEngForo);
        TextView tvUsuarioAsk = view.findViewById(R.id.tvUsuarioAskForo);
        TextView tvUsuarioAns = view.findViewById(R.id.tvUsuarioAnsForo);
        Button btnAudio = view.findViewById(R.id.btnReproducir);

        tvUsuarioAsk.setText(listaFrases.get(position).getAskUser());
        tvUsuarioAns.setText(listaFrases.get(position).getAnsUser());
        tvFraseEsp.setText(listaFrases.get(position).getFraseEsp());
        tvFraseEng.setText(listaFrases.get(position).getFraseEng());

        return view;
    }
}
