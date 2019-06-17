package id.ddd.sibagun.util;


import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.github.ybq.android.spinkit.style.ChasingDots;
import com.shashank.sony.fancytoastlib.FancyToast;

import id.ddd.sibagun.LoginActivity;
import id.ddd.sibagun.MainActivity;
import id.ddd.sibagun.R;

import static id.ddd.sibagun.util.DDD.getInstance;

public class CustomDialog extends Application {
    PrefManager pref;
    Context mContext;
    Dialog p_dialog;
    Handler handler;

    public CustomDialog(Context context) {
        this.mContext = context;
        pref = new PrefManager(mContext);
        init_p_dialog();
        handler = new Handler();
    }

    final String GetString(int string){
        return mContext.getString(string);
    }

    ///TOAST
    public void toastWarning(String msg){
        FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show();
    }
    public void toastError(String msg){
        FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }
    public void toastSuccess(String msg){
        FancyToast.makeText(mContext, msg, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void init_p_dialog() {
        p_dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        p_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
        p_dialog.setContentView(R.layout.dialog_progress);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(p_dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        p_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        p_dialog.getWindow().setAttributes(lp);
        ProgressBar progressBar = (ProgressBar) p_dialog.findViewById(R.id.progress);
        ChasingDots mChasingDotsDrawable = new ChasingDots();
        mChasingDotsDrawable.setColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        progressBar.setIndeterminateDrawable(mChasingDotsDrawable);
    }

    public void show_p_Dialog() {
        if (!p_dialog.isShowing())
            p_dialog.show();
    }

    public void hide_p_Dialog() {
        if (p_dialog.isShowing())
            p_dialog.dismiss();
    }

    public void oneButtonDialog(String message, int img_header, int color_header){
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
        dialog.setContentView(R.layout.dialog_one_button);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setAttributes(lp);


        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                }
                return true;
            }
        });
        Drawable img = mContext.getResources().getDrawable(img_header);
        int color = mContext.getResources().getColor(color_header);

        ((LinearLayout) dialog.findViewById(R.id.header_dialog)).setBackgroundColor(color);
        ((ImageView) dialog.findViewById(R.id.img_dialog)).setImageDrawable(img);
        ((TextView) dialog.findViewById(R.id.txt_message)).setText(message);
        ((Button) dialog.findViewById(R.id.bt_positive)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void dialog_skip_login(){
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // b// efore
        dialog.setContentView(R.layout.dialog_two_button);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setAttributes(lp);


        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                }
                return true;
            }
        });

        ((TextView) dialog.findViewById(R.id.txt_message)).setText(GetString(R.string.skip_login));
        ((Button) dialog.findViewById(R.id.bt_positive)).setText(GetString(R.string.bt_positive));
        ((Button) dialog.findViewById(R.id.bt_positive)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                show_p_Dialog();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        hide_p_Dialog();
                        pref.setSkipLogin(true);
                        pref.setLogin(false);
                        Intent i = new Intent();
                        i.setClass(mContext, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        ((Activity) mContext).finish();
                    }
                }, 1000);

            }
        });
        ((Button) dialog.findViewById(R.id.bt_negative)).setText(GetString(R.string.bt_negative));
        ((Button) dialog.findViewById(R.id.bt_negative)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void dialog_logout(){
        final Dialog dialog = new Dialog(mContext, R.style.DialogSlideAnim);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_two_button);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setAttributes(lp);


        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                }
                return true;
            }
        });

        ((TextView) dialog.findViewById(R.id.txt_message)).setText(GetString(R.string.logout));
        ((Button) dialog.findViewById(R.id.bt_positive)).setText(GetString(R.string.bt_logout));
        ((Button) dialog.findViewById(R.id.bt_positive)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                show_p_Dialog();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        hide_p_Dialog();
                        getInstance().clearPref();
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));
                        ((Activity) mContext).finish();
                    }
                }, 1000);


            }
        });
        ((Button) dialog.findViewById(R.id.bt_negative)).setText(GetString(R.string.bt_negative));
        ((Button) dialog.findViewById(R.id.bt_negative)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
