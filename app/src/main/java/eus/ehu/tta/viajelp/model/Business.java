package eus.ehu.tta.viajelp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import prof.comms.RestClient;

/**
 * Created by edwin on 3/01/18.
 */

public class Business {


    public RestClient restClient;
    public JSONTools jsonTools;

    //Para borrar seguramente
    List<Frase> listaFrases;

    public Business (){
        crearLista();
        restClient = new RestClient();
        jsonTools = new JSONTools();
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

    public Usuario loginPost(String usuario, String password)  {

        //RestClient rc = new RestClient();
        JSONObject json = new JSONObject();

        try {
            json.put("usuario",usuario);
            json.put("password",password);

            Usuario user = jsonTools.getUsuarioFromJson(restClient.postJsonConnection(json,restClient.LOGIN_URL));

            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
