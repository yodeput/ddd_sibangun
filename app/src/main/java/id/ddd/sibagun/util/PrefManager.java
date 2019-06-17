package id.ddd.sibagun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class PrefManager {
    private static final String PREF_NAME = "sibangunMobile_pref";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_SKIP_LOGGED_IN = "skipLoggedIn";
    private static String TAG = PrefManager.class.getSimpleName();
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public void setSkipLogin(boolean skipLoggedIn) {
        editor.putBoolean(KEY_SKIP_LOGGED_IN, skipLoggedIn);
        editor.commit();
    }

    public void addPref(String tag, String value){
        editor.putString(tag, value);
        editor.commit();
    }

    public String getPref(String tag){
        String value="";
        value = pref.getString(tag,"");
        return value;
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean skipLoggedIn() {
        return pref.getBoolean(KEY_SKIP_LOGGED_IN, false);
    }

}
