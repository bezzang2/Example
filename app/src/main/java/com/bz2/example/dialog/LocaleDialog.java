package com.bz2.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.bz2.example.R;

public class LocaleDialog extends Dialog {
    OnLocaleListener listener;

    public interface OnLocaleListener{
        public void onSelected(String code);
    }

    public LocaleDialog(Context context, OnLocaleListener listener){
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setCancelable(false);

        setContentView(R.layout.dialog_locale);

        View btn = findViewById(R.id.btn_ko);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected("ko");
                dismiss();
            }
        });

        btn = findViewById(R.id.btn_en);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected("en");
                dismiss();
            }
        });

        btn = findViewById(R.id.btn_np);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected("ne");
                dismiss();
            }
        });

        btn = findViewById(R.id.btn_vn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected("vi");
                dismiss();
            }
        });

        btn = findViewById(R.id.btn_kh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelected("ca");
                dismiss();
            }
        });
    }
}
