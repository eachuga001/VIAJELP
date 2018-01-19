package prof.comms;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by edwin on 3/01/18.
 */

public class RestClient {
    public final String URL_SERVER = "http://158.227.55.34:28080/serverViajelp/";//EHU PUBLIC
    public final String LOGIN_URL = URL_SERVER+"rest/appServ/loginPost";
    public final String REGISTRO_URL = URL_SERVER+"rest/appServ/registroPost";
    public final String LISTA_FRASES_URL = URL_SERVER+"rest/appServ/listaFrasesByTipo";
    public final String UP_FRASE_ASK_URL = URL_SERVER+"rest/appServ/upFraseAsk";
    public final String UP_FRASE_ANS_URL = URL_SERVER+"rest/appServ/upFraseAns";
    public final String LISTA_PALABRAS_URL = URL_SERVER+"rest/appServ/listaPalabras";
    public final String UP_PALABRA_URL = URL_SERVER+"rest/appServ/upPalabra";


    private HttpURLConnection getConnection(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        return conn;
    }

    public String postJsonConnection(final JSONObject json, String path) throws IOException {
        HttpURLConnection conn = null;

        conn = getConnection(path);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(json.toString());
        writer.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        //if(br.readLine()!=null)
            System.out.println(conn.getResponseMessage());

        return br.readLine();
    }

}
