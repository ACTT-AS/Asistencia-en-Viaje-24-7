package com.appcode.ea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.appcode.ea.utils.AlertDialogManager;
import com.appcode.ea.utils.ConnectionDetector;
import com.appcode.ea.utils.GpsTracker;

/**
 * Created by claudioandrescarcamocorrea on 07-09-14.
 */
public class CareMenuSplashActivity extends Activity {
    ConnectionDetector cd;
    GpsTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //Log.i("APPCODE","SPLASH DE LA APP");
        super.onCreate(savedInstanceState);
        //no muestra barra superior
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //fin no muestra barra superior
        setContentView(R.layout.principal_splash);
        //Log.i("DISPOSITIVO ID >>",getIdPhone());

        /*Detectamos la Conexión*/
        cd = new ConnectionDetector(getApplicationContext());
        AlertDialogManager alert = new AlertDialogManager();

        if (!cd.isConnectingToInternet()) {
            //Log.i("APPCODE","Error : Conexión Internet");
            alert.showAlertDialog(CareMenuSplashActivity.this, "Error : Conexión Internet",
                    "Asegurese que el dispositivo tenga internet.", false);
            return;
        }
        /*Fin de Detección*/

        gps = new GpsTracker(CareMenuSplashActivity.this);
        if(gps.canGetLocation()){
            //Log.i("APPCODE","GPS ACTIVO");
        }else{
            gps.showSettingsAlert();
            //Log.i("APPCODE","GPS INACTIVO");
        }

        final ImageButton viaje = (ImageButton) findViewById(R.id.btn1);
        final ImageButton vehicular = (ImageButton) findViewById(R.id.btn2);

       vehicular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(i);
            }
        });

        viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SplashActivityAV.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
