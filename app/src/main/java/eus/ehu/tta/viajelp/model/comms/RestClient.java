package eus.ehu.tta.viajelp.model.comms;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by edwin on 3/01/18.
 */

public class RestClient {
    public final static String URL_SERVER = "http://158.227.55.34:28080/serverViajelp/";//EHU PUBLIC
    //public final static String URL_SERVER = "http://192.168.0.14:8080/serverViajelp/";//CASA
    public final static String URLS_SERVER = "http://158.227.55.34:28080/static/serverViajelp/";
    public final static String UPLOAD_FILE_URL = "http://158.227.55.34:28080/serverViajelp/rest/appServ/uploadFile";
    public final String LOGIN_URL = URL_SERVER+"rest/appServ/loginPost";
    public final String REGISTRO_URL = URL_SERVER+"rest/appServ/registroPost";
    public final String LISTA_FRASES_URL = URL_SERVER+"rest/appServ/listaFrasesByTipo";
    public final static String UP_FRASE_ASK_URL = URL_SERVER+"rest/appServ/upFraseAsk";
    public final static String UP_FRASE_ANS_URL = URL_SERVER+"rest/appServ/upFraseAns";
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

    public String postJsonConnectionUp(final JSONObject json, String path) throws IOException {
        HttpURLConnection conn = null;

        conn = getConnection(path);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(json.toString());
        writer.close();

        //BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        //if(br.readLine()!=null)
        System.out.println(conn.getResponseMessage());

        return conn.getResponseMessage();
    }


    public int postFile2(String path, InputStream is, String fileName) throws IOException{
        String boundary = Long.toString(System.currentTimeMillis());
        String newLine = "\r\n";
        String prefix = "--";
        String fileType;

        if(fileName.contains("jpg")|| fileName.contains("png")||fileName.contains("gif")){
            fileType="img";
        }
        else{
            fileType="audio";
        }

        HttpURLConnection c = null;
        try{
            c = getConnection(path);
            c.setRequestMethod("POST");
            c.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            c.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(c.getOutputStream());
            out.writeBytes(prefix+boundary+newLine);
            out.writeBytes("Content-Disposition: form-data; name=\"filetype\"");
            out.writeBytes(newLine);
            out.writeBytes(newLine);
            out.writeBytes(fileType);
            out.writeBytes(newLine);
            out.writeBytes(prefix+boundary+newLine);
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"" + newLine);
            out.writeBytes("Content-Type: multipart/form-data" + newLine);
            out.writeBytes(newLine);
            byte[] data = new byte[1024 * 1024];
            int len;
            while((len = is.read(data)) > 0){
                out.write(data,0,len);
            }
            out.writeBytes(newLine);
            out.writeBytes(prefix+boundary+prefix+newLine);
            out.close();
            return c.getResponseCode();
        }
        finally{
            if(c != null){
                c.disconnect();
            }
        }
    }

}
