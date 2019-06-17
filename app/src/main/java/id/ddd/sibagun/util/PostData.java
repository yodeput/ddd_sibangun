package id.ddd.sibagun.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.ddd.sibagun.MainActivity;
import id.ddd.sibagun.R;

import static id.ddd.sibagun.util.DDD.getInstance;
import static id.ddd.sibagun.util.URLConfig.LOGIN;

public class PostData extends Application {
    Context mContext;
    CustomDialog cd;
    PrefManager pref;
    SaveData saveData;
    String error_koneksi;
    public PostData(Context context){
        this.mContext = context;
        pref = new PrefManager(mContext);
        cd = new CustomDialog(mContext);
        saveData = new SaveData(mContext);
        error_koneksi = mContext.getString(R.string.error_koneksi);
    }

    public void login(final String username, final String password) {
        cd.show_p_Dialog();
        String tag_string_req = "req_login";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                cd.hide_p_Dialog();
                Log.d("Response", response);
                try {
                    JSONObject jObj = new JSONObject(response);
                    Boolean error = jObj.getBoolean("login");
                    String msg  = jObj.getString("msg");
                    if (error) {
                        String data = jObj.getString("data");
                        saveData.data_login(data);
                        pref.setLogin(true);
                        pref.setSkipLogin(false);
                        Intent i = new Intent();
                        i.setClass(mContext, MainActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                        ((Activity) mContext).finish();
                    } else {
                        msg = msg.replace("..<br>","\n");
                        cd.oneButtonDialog(msg,R.drawable.ic_error_x,R.color.orange_800);
                    }
                } catch (final JSONException e) {
                    e.printStackTrace();
                    cd.oneButtonDialog(error_koneksi,R.drawable.ic_error_con,R.color.orange_800);
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cd.oneButtonDialog(error_koneksi,R.drawable.ic_error_con,R.color.orange_800);
                        Log.d("Error", error.toString());
                        cd.hide_p_Dialog();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("rooter_ip", getInstance().getLocalIpAddress());
                params.put("local_ip", "");
                return params;
            }
        };

        getInstance().addToRequestQueue(strReq, tag_string_req);
    }

}
