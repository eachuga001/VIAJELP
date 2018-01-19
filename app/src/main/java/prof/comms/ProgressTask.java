package prof.comms;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by edwin on 15/01/18.
 */

public abstract class ProgressTask <T> extends AsyncTask<Void, Void, T> {
    protected final Context context;
   // private final ProgressDialog dialog;
    private Exception e;

    protected ProgressTask(Context context) {
        this.context = context;
        //this.dialog = dialog;
        //dialog.setMessage("Conectando...");
    }

    @Override
    protected void onPreExecute(){
        //if(dialog!=null)
           // dialog.show();
    }

    @Override
    protected T doInBackground(Void... params) {
        T result = null;
        try {
            result = work();
        }catch (Exception e){
            this.e = e;
        }
        return result;
    }

    @Override
    protected void onPostExecute(T result){
        //if(dialog.isShowing())
         //   dialog.dismiss();
        if(e != null)
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        else
            onFinish(result);
    }
    protected abstract T work() throws Exception;
    protected abstract void onFinish(T result);
}
