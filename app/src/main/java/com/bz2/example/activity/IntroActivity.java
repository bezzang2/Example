package com.bz2.example.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.bz2.example.R;
import com.bz2.example.common.Const;
import com.bz2.example.fragment.SplashFragment;

public class IntroActivity  extends FragmentActivity {

    Context context;
    Handler handler;
    SplashFragment fragment;

    boolean bPush = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Object v = extras.get(key);
                Log.d("BZ2", "{" + key + ":" + v);

                if (key.equals("push_title")) {
                    bPush = true;
                }
            }
        }

        if (bPush) {
            finish();
            return;
        }

        setContentView(R.layout.activity_intro);

        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction fTransaction = fManager.beginTransaction();

        this.fragment = new SplashFragment();
        fTransaction.replace(R.id.container, this.fragment);
        fTransaction.commit();
    }

    public void onRequestPermissionsResult(int requestCode, @Nullable String[] permissions, @Nullable int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);

        Log.d("BZ2", "onRequestPermissionsResult == start");

        boolean isPermissionGranted = true;

        if( requestCode == Const.Permission.REQUEST_PERMISSION){
            for( int i=0 ; i<permissions.length ; i++){
                String permission = permissions[i];
                Log.d("BZ2", "permission = " + permission);

                if(grantResult[i] != PackageManager.PERMISSION_GRANTED){
                    isPermissionGranted = false;
                    break;
                }
            }

            if( isPermissionGranted){
                fragment.continueProcess();
            }
            else{
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
    }
}
