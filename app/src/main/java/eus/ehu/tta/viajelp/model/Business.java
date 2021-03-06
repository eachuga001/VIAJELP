package eus.ehu.tta.viajelp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import eus.ehu.tta.viajelp.model.beans.Frase;
import eus.ehu.tta.viajelp.model.beans.Usuario;
import eus.ehu.tta.viajelp.model.comms.RestClient;

/**
 * Created by edwin on 3/01/18.
 */

public class Business {


    public RestClient restClient;
    public JSONTools jsonTools;

    //Para borrar seguramente
    List<Frase> listaFrases;

    public Business (){
        //getFrasesSituaciones();
        //crearLista();
        restClient = new RestClient();
        jsonTools = new JSONTools();
    }

    public Boolean sendAudio(InputStream inputStream, String filename) throws IOException {

        int httpResponseCode;

        if (inputStream != null && filename != null && !filename.isEmpty()) {
            httpResponseCode = restClient.postFile2(RestClient.UPLOAD_FILE_URL, inputStream, filename);
            System.out.println("LA RESPUESTA ES: "+httpResponseCode);
            if (httpResponseCode == HttpURLConnection.HTTP_OK || httpResponseCode == HttpURLConnection.HTTP_NO_CONTENT)
                return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public Boolean sendAudio2(InputStream inputStream, String filename) throws IOException {

        int httpResponseCode;
        System.out.println("LA URI TIENE EL SIGUIENTE FILENAME: "+filename );
        if (inputStream != null && filename != null && !filename.isEmpty()) {
            System.out.println("LA URI TIENE EL SIGUIENTE FILENAME: "+filename );
        }

        return Boolean.FALSE;
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

    public void getFrasesSituaciones(){
        if(listaFrases == null)
            listaFrases = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("tipo","S");
            String frasesServidor = restClient.postJsonConnection(jsonObject,restClient.LISTA_FRASES_URL);
            listaFrases = jsonTools.getFrasesFromJson(frasesServidor);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPalabrasFromServer(){
        String palabras = null;
        //restClient.postJsonConnection()

        return palabras;
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

            Usuario user = null;
            String responseFromServer = restClient.postJsonConnection(json,restClient.LOGIN_URL);
            if(responseFromServer!=null)
                user = jsonTools.getUsuarioFromJson(responseFromServer);

            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
