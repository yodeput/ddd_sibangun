package id.ddd.sibagun.util;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import id.ddd.sibagun.R;

public class DDD extends Application {

    public static final String TAG = DDD.class.getSimpleName();
    private static DDD mInstance;
    private RequestQueue mRequestQueue;
    private PrefManager prefManager;
    public static synchronized DDD getInstance() {
        return mInstance;
    }

    public static void hideSoftKeyboard(Activity pActivity) {
        if (pActivity == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager)
                pActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View viewWithCurrentFocus = pActivity.getCurrentFocus();
        if (inputMethodManager != null && viewWithCurrentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    viewWithCurrentFocus.getWindowToken(), 0);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefManager = new PrefManager(this.getApplicationContext());
        mInstance = this;



    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        Log.i(TAG, "***** IP=" + ip);
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e(TAG, ex.toString());
        }
        return null;
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void clearPref(){
        SharedPreferences preferences = getSharedPreferences("sibangunMobile_pref", 0);
        preferences.edit().clear().apply();
    }
}
