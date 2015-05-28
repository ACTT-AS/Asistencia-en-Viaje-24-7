package com.appcode.ea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.appcode.ea.utils.AlertDialogManager;
import com.appcode.ea.utils.ConnectionDetector;
import com.appcode.ea.utils.GpsTracker;

/**
 * Created by claudioandrescarcamocorrea on 07-09-14.
 */
public class SplashActivityAV extends Activity {
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
        setContentView(R.layout.activity_splash_av);
        //Log.i("DISPOSITIVO ID >>",getIdPhone());

        /*Detectamos la Conexión*/
        cd = new ConnectionDetector(getApplicationContext());
        AlertDialogManager alert = new AlertDialogManager();

        if (!cd.isConnectingToInternet()) {
            //Log.i("APPCODE","Error : Conexión Internet");
            alert.showAlertDialog(SplashActivityAV.this, "Error : Conexión Internet",
                    "Asegurese que el dispositivo tenga internet.", false);
            return;
        }
        /*Fin de Detección*/

        gps = new GpsTracker(SplashActivityAV.this);
        if(gps.canGetLocation()){
            //Log.i("APPCODE","GPS ACTIVO");
        }else{
            gps.showSettingsAlert();
            //Log.i("APPCODE","GPS INACTIVO");
        }

        final Button entrar = (Button) findViewById(R.id.btn_continuar);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EuropAssistanceAV.class);
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

