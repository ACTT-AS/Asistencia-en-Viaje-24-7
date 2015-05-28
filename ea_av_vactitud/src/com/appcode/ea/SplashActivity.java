package com.appcode.ea;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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
public class SplashActivity extends Activity {
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
        setContentView(R.layout.activity_splash);
        //Log.i("DISPOSITIVO ID >>",getIdPhone());

        /*Detectamos la Conexión*/
        cd = new ConnectionDetector(getApplicationContext());
        AlertDialogManager alert = new AlertDialogManager();

        if (!cd.isConnectingToInternet()) {
            //Log.i("APPCODE","Error : Conexión Internet");
            alert.showAlertDialog(SplashActivity.this, "Error : Conexión Internet",
                    "Asegurese que el dispositivo tenga internet.", false);
            return;
        }
        /*Fin de Detección*/

        gps = new GpsTracker(SplashActivity.this);
        if(gps.canGetLocation()){
            //Log.i("APPCODE","GPS ACTIVO");
        }else{
            gps.showSettingsAlert();
            //Log.i("APPCODE","GPS INACTIVO");
        }

        final Button entrar = (Button) findViewById(R.id.btn_continuar);

        /*Thread timer = new Thread(){
            public void run() {
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(getApplicationContext(), EuropAssistance.class);
                    startActivity(i);
                }
            };
        };
        timer.start();*/

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), EuropAssistance.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    /**
     *  Método para crear un id por dispositivo
     *  Autor:ccarcamocorrea
     * @return String
     */
    protected static String getIdPhone(){
        String m_szDevIDShort = "69" +
                Build.BOARD.length()%10+ Build.BRAND.length()%10 +
                Build.CPU_ABI.length()%10 + Build.DEVICE.length()%10 +
                Build.DISPLAY.length()%10 + Build.HOST.length()%10 +
                Build.ID.length()%10 + Build.MANUFACTURER.length()%10 +
                Build.MODEL.length()%10 + Build.PRODUCT.length()%10 +
                Build.TAGS.length()%10 + Build.TYPE.length()%10 +
                Build.USER.length()%10 ; //13 digitos
        return m_szDevIDShort;
    }


}
