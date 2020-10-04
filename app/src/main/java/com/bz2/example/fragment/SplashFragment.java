package com.bz2.example.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bz2.example.Bz2;
import com.bz2.example.MainActivity;
import com.bz2.example.R;
import com.bz2.example.common.BuildConfig;
import com.bz2.example.common.Const;
import com.bz2.example.dialog.AlertDialog;
import com.bz2.example.dialog.LocaleDialog;
import com.bz2.example.dialog.PermissionDialog;
import com.bz2.example.manager.PreferenceManager;

public class SplashFragment extends Fragment {
    private PreferenceManager manager;
    ImageView ivLoader;
    Handler handler;

    public SplashFragment(){
        //  do nothing
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        this.handler = new Handler();
        manager = new PreferenceManager(getContext());

        this.ivLoader = view.findViewById(R.id.img_progress);
        this.ivLoader.setVisibility(View.INVISIBLE);

        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkApp();
            }
        }, 300);

        return view;
    }

    private void checkApp(){
        if(BuildConfig.DEBUG){
            procStart();
            return;
        }
    }

    private void procStart(){
        String code = this.manager.getLanguageCode();
        if( code != null ){
            checkPermission();
        }
        else{
            LocaleDialog dialog = new LocaleDialog(getContext(), new LocaleDialog.OnLocaleListener(){
                @Override
                public void onSelected(String code){
                    manager.setLanguageCode(code);
                    ((Bz2)getActivity().getApplicationContext()).setLanguage(code);

                    handler.postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            checkPermission();
                        }
                    }, 500);
                }
            });

            dialog.show();
        }
    }

    private void checkPermission(){
        Log.d("BZ2", "checkPermission -- START");
        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion >= Build.VERSION_CODES.LOLLIPOP)
        {
            int cameraPermission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
            int writeExternalPermission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int callPhonePermission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);
            int readExternalPermission = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if( cameraPermission != PackageManager.PERMISSION_GRANTED ||
                    writeExternalPermission != PackageManager.PERMISSION_GRANTED ||
                    callPhonePermission != PackageManager.PERMISSION_GRANTED ||
                    readExternalPermission != PackageManager.PERMISSION_GRANTED )
            {
                //loader.setVisibility(View.VISIBLE);
                //((AnimationDrawable)loader.getBackground()).start();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reqPermission();
                    }
                }, 1000);
                return;
            }
            else
            {
                continueProcess();
            }
        }
        else{
            continueProcess();
        }
    }

    private void reqPermission(){
        Log.d("BZ2", "reqPermission -- START");

        PermissionDialog dialog = new PermissionDialog(getContext());
        dialog.posListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String[] list = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions(getActivity(), list, Const.Permission.REQUEST_PERMISSION);
            }
        };

        dialog.negListener = new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog dialog1 = new AlertDialog(getContext());
                dialog1.title = getString(R.string.permission_deny_title);

                final SpannableString sb = new SpannableString(getString(R.string.permission_deny_desc));
                final StyleSpan ss = new StyleSpan(Typeface.BOLD);

                dialog1.spanned = sb;
                dialog1.negativeBtnText = getString(R.string.common_no);
                dialog1.positiveBtnText = getString(R.string.common_yes);

                dialog1.positiveListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        getActivity().finish();
                    }
                };

                dialog1.negativeListener = new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                    }
                };

                dialog1.show();
            }
        };

        dialog.show();
    }

    public void continueProcess(){
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                goNextActivity();
            }
        }, 1000);
    }

    public void goNextActivity(){
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        if(BuildConfig.NEED_URL){
            intent.putExtra("URL", ((Bz2)getActivity().getApplicationContext()).BASE_URL + Const.Url.LOGOUT_MAIN_URL);
        }

        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
