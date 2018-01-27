package eus.ehu.tta.viajelp.presentation.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import eus.ehu.tta.viajelp.R;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.comms.BackgroundThread;
import eus.ehu.tta.viajelp.model.comms.RestClient;

/**
 * Created by edwin on 26/01/18.
 */

public class DialogPlayer extends DialogFragment implements Runnable {

    static int idUsuario,position;
    static String frase;
    static String type;
    private EditText etNuevaFrase;

    public DialogPlayer(){

    }
    public static DialogPlayer newInstance (String string,int id,String tipo){
        DialogPlayer dp = new DialogPlayer();
        idUsuario = id;
        frase = string;
        type = tipo;

        return dp;
    }

    public static DialogPlayer newInstance (int id,String tipo){
        DialogPlayer dp = new DialogPlayer();
        idUsuario = id;
        type = tipo;

        return dp;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        etNuevaFrase = getActivity().findViewById(R.id.etPreguntaForo);
        switch (type){
            case "dialogoPreguntar":
                builder = getDialogPreguntar(builder);
                break;
            case "dialogoResponder":
                break;
            case "dialogoReproducir":
                builder = getDialogReproducir(builder);
                break;
        }

        return builder.create();

    }

    public AlertDialog.Builder getDialogPreguntar(AlertDialog.Builder builder){
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View layout = inflater.inflate(R.layout.dialog_pregutar_foro,null);
        etNuevaFrase = layout.findViewById(R.id.etPreguntaForo);
        builder.setView(layout);
        builder.setTitle(R.string.pregutaAlForo).
                setPositiveButton(R.string.preguntar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Lo que debe hacer tras la confirmacion
                        JSONTools jsonTools = new JSONTools();

                        String fraseJson = jsonTools.getJsonFromFrase(jsonTools.buildFraseAsk(etNuevaFrase.getText().toString(),idUsuario));

                        BackgroundThread hilo = new BackgroundThread(fraseJson);

                        hilo.execute(RestClient.UP_FRASE_ASK_URL);
                        //hilo.execute("http://192.168.43.203:8080/TFG/rest");

                        getActivity().onBackPressed();
                        dialog.cancel();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que debe hacer tras la cancelacion

                dialog.cancel();
            }
        });

        return builder;
    }

    public AlertDialog.Builder getDialogReproducir(AlertDialog.Builder builder){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout =  inflater.inflate(R.layout.dialog_reproducir_foro,null);

        int position =  idUsuario;//en este caso en el constructor se pasa la posicion, pero no el idUsuairo

        AudioPlayer player = new AudioPlayer(layout,this);
        try {
            //player.setAudioUri(Uri.parse(RestClient.URLS_SERVER+"audio/audioForo"+position+".3gp"));
            player.setAudioUri(Uri.parse("http://158.227.55.34:28080/static/serverViajelp/audio/audioForo3.3gp"));
            //player.setAudioUri(Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
            //player.setAudioUri("http://158.227.55.34:28080/static/serverViajelp/audio/audioForo3.3gp");

            //player.setAudioUri(Uri.parse("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
        }catch (IOException e){
            e.printStackTrace();
        }

        builder.setView(layout);
        builder.setTitle(R.string.reproducir);

        return builder;
    }

    @Override
    public void run() {

    }
}
