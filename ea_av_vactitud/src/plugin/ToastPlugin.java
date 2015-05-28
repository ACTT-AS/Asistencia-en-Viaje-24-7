package plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastPlugin extends CordovaPlugin {

    final String LOG_TAG = "ToastLog";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        Log.d(LOG_TAG, "Start Toast");

        final String toastText = args.getString(0);
        final int toastDuration = args.getInt(1);
        final CallbackContext ctx = callbackContext;

        Runnable runnable = new Runnable() {
            public void run() {

                Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), toastText, toastDuration);
                //toast.getView().getBackground().setColorFilter(Color.parseColor("#314397"), PorterDuff.Mode.SCREEN);
                TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                toast.getView().setBackgroundColor(Color.parseColor("#314397"));
                toast.show();
                toast.show();
                toast.show();
                ctx.success();
            }
        };

        cordova.getActivity().runOnUiThread(runnable);

        return true;
    }


}