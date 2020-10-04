package com.bz2.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.bz2.example.R;
import com.bz2.example.common.Const;

public class PermissionDialog extends Dialog {

    private View btnPositive;
    private View btnNegative;

    public View.OnClickListener posListener;
    public View.OnClickListener negListener;

    public PermissionDialog(Context context){
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);

        setContentView(R.layout.dialog_permission);

        this.btnPositive = findViewById(R.id.btn_ok);
        this.btnPositive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(posListener != null ){
                    posListener.onClick(v);
                }

                dismiss();
            }
        });

        btnNegative = findViewById(R.id.btn_close);
        btnNegative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(negListener != null ){
                    negListener.onClick(v);
                }
            }
        });
    }
}
