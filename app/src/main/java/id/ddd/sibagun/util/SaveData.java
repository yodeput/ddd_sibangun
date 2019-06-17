package id.ddd.sibagun.util;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import static id.ddd.sibagun.util.DDD.getInstance;

public class SaveData {

    Context mContext;
    CustomDialog cd;
    PrefManager pref;

    public SaveData(Context context){
        this.mContext = context;
        pref = new PrefManager(mContext);
        cd = new CustomDialog(mContext);
    }

    public void data_login(String data){
        try {
            JSONObject jObj = new JSONObject(data);
            pref.addPref("SESS_ID",jObj.getString("SESS_ID"));
            pref.addPref("SESS_USERNAME",jObj.getString("SESS_USERNAME"));
            pref.addPref("SESS_NAMA",jObj.getString("SESS_NAMA"));
            pref.addPref("SESS_LEVEL",jObj.getString("SESS_LEVEL"));
            pref.addPref("SESS_EMAIL",jObj.getString("SESS_EMAIL"));
            pref.addPref("SESS_TELP",jObj.getString("SESS_TELP"));
            pref.addPref("SESS_ID_KAB",jObj.getString("SESS_ID_KAB"));
            pref.addPref("SESS_NM_KAB",jObj.getString("SESS_NM_KAB"));
            pref.addPref("SESS_ID_SKPD",jObj.getString("SESS_ID_SKPD"));
            pref.addPref("SESS_ID_IZIN",jObj.getString("SESS_ID_IZIN"));
            pref.addPref("SESS_IZIN_HAPUS",jObj.getString("SESS_IZIN_HAPUS"));
            pref.addPref("SESS_FOTO",jObj.getString("SESS_FOTO"));
            pref.addPref("SESS_TAHUN",jObj.getString("SESS_TAHUN"));
            pref.addPref("last_update",getInstance().getDateTime());
        } catch (final JSONException e) {
            e.printStackTrace();

        }
    }
}
