package eus.ehu.tta.viajelp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edwin on 3/01/18.
 */

public class Business {


    List<Frase> listaFrases;

    public Business (){
        crearLista();
    }


    public void crearLista(){
        String[] frase1 = {"Hola","Hi"};
        String[] frase2 = {"Adios","Bye"};

        if(listaFrases == null)
            listaFrases = new ArrayList<>();

        Frase fraseA = new Frase();
        Frase fraseB = new Frase();
        fraseA.setFraseEsp(frase1[0]);
        fraseA.setFraseEng(frase1[1]);
        listaFrases.add(fraseA);
        fraseB.setFraseEsp(frase2[0]);
        fraseB.setFraseEng(frase2[1]);
        listaFrases.add(fraseB);
    }

    public List<Frase> getListaFrases() {
        return listaFrases;
    }

    public void setListaFrases(List<Frase> listaFrases) {
        this.listaFrases = listaFrases;
    }
}
