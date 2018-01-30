package eus.ehu.tta.viajelp.presentation.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import eus.ehu.tta.viajelp.R;
import eus.ehu.tta.viajelp.model.Business;
import eus.ehu.tta.viajelp.model.beans.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import eus.ehu.tta.viajelp.model.comms.BackgroundThread;
import eus.ehu.tta.viajelp.model.comms.ProgressTask;
import eus.ehu.tta.viajelp.model.comms.RestClient;

/**
 * Created by edwin on 26/01/18.
 */

public class DialogPlayer extends DialogFragment implements Runnable {

    static int idUsuario,position;
    static Frase frase;
    static String type;
    private EditText etNuevaFrase;
    private static final int AUDIO_REQUEST_CODE = 2;
    public Uri fileUri;
    String fileName;
    private Button btnGrabarAudio,btnReproducirAudio,btnEnviarAudio;
    private static Activity activity;

    public DialogPlayer(){

    }
    public static DialogPlayer newInstance (Activity a,Frase f,int id,int posicion,String tipo){
        DialogPlayer dp = new DialogPlayer();
        idUsuario = id;
        frase = f;
        type = tipo;
        position = posicion;
         activity = a;

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

        //etNuevaFrase = getActivity().findViewById(R.id.etPreguntaForo);
        switch (type){
            case "dialogoPreguntar":
                builder = getDialogPreguntar(builder);
                break;
            case "dialogoResponder":
                fileName = "audioForo"+position+".mp3";
                builder = getDialogResponder(builder);
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

    public AlertDialog.Builder getDialogResponder(AlertDialog.Builder builder){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View layout =  inflater.inflate(R.layout.dialog_responder_foro,null);

        TextView tvPreguntaForo =  layout.findViewById(R.id.tvPreguntaForo);
        etNuevaFrase = layout.findViewById(R.id.etRespuestaForo);
        btnGrabarAudio = layout.findViewById(R.id.btnGrabarAudio);
        btnReproducirAudio =  layout.findViewById(R.id.btnReproducirAudio);

        if(frase.getAudio()!="null")
            btnReproducirAudio.setVisibility(Button.VISIBLE);

        btnGrabarAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
                    Toast.makeText(getContext(),R.string.noMicrophone,Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                    if(intent.resolveActivity(getActivity().getPackageManager())!=null)
                        startActivityForResult(intent, AUDIO_REQUEST_CODE);
                    else
                        Toast.makeText(getContext(),R.string.noApp,Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReproducirAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CODIGO PARA REPRODUCIR UN AUDIO DEL SERVIDOR
                //sendResponse(fileUri);

                AudioPlayer player = getAudioPlayer(layout);
                try {
                    player.setAudioUri(Uri.parse(RestClient.URLS_SERVER+"audio/audioForo"+position+".mp3"));
                    //player.setAudioUri(Uri.parse("http://158.227.55.34:28080/static/serverViajelp/audio/audioForo3.3gp"));
                    //player.setAudioUri(Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"));
                    //player.setAudioUri("http://158.227.55.34:28080/static/serverViajelp/audio/audioForo3.3gp");

                    //player.setAudioUri(Uri.parse("http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        btnEnviarAudio = layout.findViewById(R.id.btnEnviarAudio);
        btnEnviarAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResponse(fileUri);
            }
        });

        tvPreguntaForo.setText(frase.getFraseEsp());
        builder.setView(layout);
        builder.setTitle(R.string.contestaAlForo);
        /*builder.setPositiveButton(R.string.responder, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //lo que debe hacer al presionar el boton de responder
                        JSONTools jsonTools = new JSONTools();

                        Frase f = jsonTools.buildFraseAns(frase,etNuevaFrase.getText().toString(),idUsuario,fileName);
                        String fraseJson = jsonTools.getJsonFromFrase(f);

                        sendResponse(fileUri);
                        BackgroundThread hilo = new BackgroundThread(fraseJson);
                        hilo.execute(RestClient.UP_FRASE_ANS_URL);

                        //getActivity().onBackPressed();
                        //dialog.cancel();

                    }
                });*/

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK)
            return;
        fileUri = data.getData();
        btnEnviarAudio.setVisibility(Button.VISIBLE);//esto para quitar

        Toast.makeText(getContext(),"Audio Guardado",Toast.LENGTH_SHORT).show();
    }

    public AlertDialog.Builder getDialogReproducir(AlertDialog.Builder builder){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout =  inflater.inflate(R.layout.dialog_reproducir_foro,null);

        AudioPlayer player = new AudioPlayer(layout,this);
        try {
            player.setAudioUri(Uri.parse(RestClient.URLS_SERVER+"audio/audioForo"+position+".3gp"));
            //player.setAudioUri(Uri.parse("http://158.227.55.34:28080/static/serverViajelp/audio/audioForo3.3gp"));
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

    public AudioPlayer getAudioPlayer(View layout){
        AudioPlayer player = new AudioPlayer(layout,this);

        return player;
    }

    @Override
    public void run() {

    }

    private void sendResponse(final Uri uri){
        new ProgressTask<Boolean>(getContext()) {
            @Override
            protected Boolean work() throws Exception {
                InputStream inputStream = null;

                try{
                    inputStream = activity.getContentResolver().openInputStream(uri);
                    Business business = new Business();
                    return business.sendAudio(inputStream,fileName);

                }finally {
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        }catch (IOException e){

                        }
                    }
                }

            }

            @Override
            protected void onFinish(Boolean result) {
                if(result)
                    Toast.makeText(getContext(),R.string.fraseEnviada,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),R.string.errorEnviando,Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}
