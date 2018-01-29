package eus.ehu.tta.viajelp.model.comms;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by edwin on 26/01/18.
 */

public class BackgroundThread extends AsyncTask<String,Boolean,String> {
    String jsonString;
    JSONObject objetoJson;

    public BackgroundThread(String stringJson){
        jsonString=stringJson;
    }

    @Override
    protected String doInBackground(String... params) {
         RestClient cr =  new RestClient();
        //modificar esta funcion

        try {
            objetoJson = new JSONObject(jsonString);
            String responseConection = cr.postJsonConnectionUp(objetoJson,params[0]);
            System.out.println(objetoJson.toString());
            System.out.println(responseConection);

        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }


}