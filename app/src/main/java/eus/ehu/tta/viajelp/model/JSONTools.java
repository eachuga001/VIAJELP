package eus.ehu.tta.viajelp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.viajelp.model.beans.Frase;
import eus.ehu.tta.viajelp.model.beans.Palabra;
import eus.ehu.tta.viajelp.model.beans.Usuario;

/**
 * Created by edwin on 3/01/18.
 */

public class JSONTools {

    public Usuario getUsuarioFromJson(String stringJson){
        Usuario usuario = new Usuario();

        JSONObject json = null;
        try {
            json = new JSONObject(stringJson);
            usuario.setIdUsuario(json.getInt("idUsuario"));
            usuario.setEdad(json.getInt("edad"));
            usuario.setNombre(json.getString("nombre"));
            usuario.setPassword(json.getString("password"));
            usuario.setUsuario(json.getString("usuario"));

            return usuario;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject getJsonFromUsuario(Usuario u){
        JSONObject json = new JSONObject();
        try {
            json.put("nombre",u.getNombre());
            json.put("edad",u.getEdad());
            json.put("password",u.getPassword());
            json.put("usuario",u.getUsuario());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json;
    }
    //*********************************************************
    //****************OPERACIONES CON PALABRAS*****************
    //*********************************************************
    public List<Palabra> getPalabrasFromJson(String jsonArray){
        List<Palabra> listaPalabras = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonArray);
            for(int i=0;i<array.length();i++){
                JSONObject json = array.getJSONObject(i);
                Palabra palabra = new Palabra();

                palabra.setIdPalabra(json.getInt("idPalabra"));
                palabra.setIdUsuario(json.getInt("idUsuario"));
                palabra.setPalabraEng(json.getString("palabraEng"));
                palabra.setPalabraEsp(json.getString("palabraEsp"));

                listaPalabras.add(palabra);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listaPalabras;
    }

    public JSONObject getPalabraJSON(String palabra,String traduccion, int idUsuario){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("palabraEng",traduccion);
            jsonObject.put("palabraEsp",palabra);
            jsonObject.put("idUsuario",idUsuario);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    //*********************************************************
    //****************OPERACIONES CON FRASES*******************
    //*********************************************************

    public Frase getFrase(String stringJson){
        Frase frase = new Frase();
        try {
            JSONObject json = new JSONObject(stringJson);

            frase.setIdFrases(json.getInt("idFrases"));
            frase.setFraseEsp(json.getString("fraseEsp"));
            frase.setFraseEng(json.getString("fraseEng"));
            frase.setAudio(json.getString("audio"));
            frase.setSituacion(json.getString("situacion"));
            frase.setTipo(json.getString("tipo"));
            frase.setUsuarioAns(json.getInt("usuAns"));
            frase.setUsuarioAsk(json.getInt("usuAsk"));
            frase.setAnsUser(json.getString("ansUser"));
            frase.setAskUser(json.getString("askUser"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return frase;
    }



    public List<Frase> getFrasesFromJson(String arrayJson){
        List<Frase> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(arrayJson);
            for(int i=0;i<array.length();i++){
                JSONObject json = array.getJSONObject(i);
                Frase frase = new Frase();

                frase.setIdFrases(json.getInt("idFrases"));
                frase.setFraseEsp(json.getString("fraseEsp"));
                frase.setFraseEng(json.getString("fraseEng"));
                frase.setAudio(json.getString("audio"));
                frase.setSituacion(json.getString("situacion"));
                frase.setTipo(json.getString("tipo"));
                frase.setUsuarioAns(json.getInt("usuarioAns"));
                frase.setUsuarioAsk(json.getInt("usuarioAsk"));
                frase.setAnsUser(json.getString("ansUser"));
                frase.setAskUser(json.getString("askUser"));

                lista.add(frase);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return lista;
    }

    public List<Frase> getFrasesBySituacion(String situacion, List<Frase> lista){
        List<Frase> listaSituacion = new ArrayList<>();
        for (Frase frase : lista){
            if(frase.getSituacion().equals(situacion))
                listaSituacion.add(frase);
        }

        return listaSituacion;
    }

    public Frase buildFraseAsk(String fraseAsk, int idUser){
        Frase frase = new Frase();

        frase.setFraseEsp(fraseAsk);
        frase.setTipo("F");
        frase.setUsuarioAsk(idUser);
        frase.setUsuarioAns(1);

        return frase;
    }

    public Frase buildFraseAns(Frase frase,String fraseAns,int idUser, String audio){


        frase.setUsuarioAns(idUser);
        frase.setFraseEng(fraseAns);
        frase.setAudio(audio);

        return frase;
    }

    public String getJsonFromFrase(Frase frase){
        JSONObject fraseJson = new JSONObject();

        try {
            fraseJson.put("idFrases",frase.getIdFrases());
            fraseJson.put("fraseEsp",frase.getFraseEsp());
            fraseJson.put("fraseEng",frase.getFraseEng());
            fraseJson.put("audio",frase.getAudio());
            fraseJson.put("situacion",frase.getSituacion());
            fraseJson.put("tipo",frase.getTipo());
            fraseJson.put("usuarioAns",frase.getUsuarioAns());
            fraseJson.put("usuarioAsk",frase.getUsuarioAsk());
            fraseJson.put("ansUser",frase.getAnsUser());
            fraseJson.put("askUser",frase.getAskUser());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return fraseJson.toString();
    }
}
