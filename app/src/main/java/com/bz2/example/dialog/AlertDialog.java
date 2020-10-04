package com.bz2.example.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.bz2.example.R;

public class AlertDialog extends Dialog implements View.OnClickListener {
    private Context context;

    public String title;
    public String msg;
    public SpannableString spanned = null;

    private View btnNegative;
    private View btnPositive;

    TextView labelNegative;
    TextView labelPositive;

    public String negativeBtnText = "";
    public String positiveBtnText = "";

    public View.OnClickListener negativeListener;
    public View.OnClickListener positiveListener;

    public AlertDialog(Context context) {
        super(context);
        context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(R.layout.dialog_alert);

        initView();
    }

    private void initView(){
        setCancelable(false);

        if(title == null || "".equals(title)){
            title = context.getString(R.string.common_notice);
        }

        if(msg == null || "".equals(msg)){
            msg = context.getString(R.string.common_no_msg);
        }
        ((TextView)findViewById(R.id.tv_title)).setText(title);
        ((TextView)findViewById(R.id.tv_msg)).setText(msg);
        if(spanned!=null) ((TextView)findViewById(R.id.tv_msg)).setText(spanned);



        if(negativeListener == null){ // 버튼 1개 짜리
            View v = findViewById(R.id.bt_two);
            v.setVisibility(View.INVISIBLE);

            btnPositive = findViewById(R.id.bt_one);
            btnPositive.setOnClickListener(this);

            if(positiveBtnText != null && !"".equals(positiveBtnText)){
                labelPositive = findViewById(R.id.label_one);
                labelPositive.setText(positiveBtnText);
            }
        }
        else{
            View v = findViewById(R.id.bt_one);
            v.setVisibility(View.INVISIBLE);

            btnPositive = findViewById(R.id.bt_positive);
            btnPositive.setOnClickListener(this);

            btnNegative = findViewById(R.id.bt_negative);
            btnNegative.setOnClickListener(this);

            if(negativeBtnText != null && !"".equals(negativeBtnText)){
                labelNegative = findViewById(R.id.label_negative);
                labelNegative.setText(negativeBtnText);
            }

            if(positiveBtnText != null && !"".equals(positiveBtnText)){
                labelPositive = findViewById(R.id.label_positive);
                labelPositive.setText(positiveBtnText);
            }
        }

        View btn = findViewById(R.id.btn_close);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(negativeListener == null){ // 버튼 1개 짜리
                    if(positiveListener != null) {
                        positiveListener.onClick(null);
                    }
                }
                else{
                    negativeListener.onClick(null);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_negative:
                if(context instanceof Activity && !((Activity)context).isFinishing()) {
                    dismiss();
                }
                if(negativeListener != null) {
                    negativeListener.onClick(view);
                }
                break;
            case R.id.bt_one:
            case R.id.bt_positive:
                if(context instanceof Activity && !((Activity)context).isFinishing()) {
                    dismiss();
                }
                if(positiveListener != null) {
                    positiveListener.onClick(view);
                }
                break;
        }
    }
}
