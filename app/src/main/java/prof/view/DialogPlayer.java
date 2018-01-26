package prof.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import eus.ehu.tta.viajelp.R;
import eus.ehu.tta.viajelp.model.Frase;
import eus.ehu.tta.viajelp.model.JSONTools;
import prof.comms.BackgroundThread;
import prof.comms.RestClient;

/**
 * Created by edwin on 26/01/18.
 */

public class DialogPlayer extends DialogFragment {

    static int idUsuario;
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
}
