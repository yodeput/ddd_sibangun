package id.ddd.sibagun.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ddd.sibagun.R;
import id.ddd.sibagun.model.TahapanItem;

import static id.ddd.sibagun.util.DDD.getInstance;
import static id.ddd.sibagun.util.URLConfig.TAHAPAN;

public class GetData {

    Context mContext;
    CustomDialog cd;
    PrefManager pref;
    SaveData saveData;
    String error_koneksi;

    public GetData(Context context) {
        this.mContext = context;
        pref = new PrefManager(mContext);
        cd = new CustomDialog(mContext);
        saveData = new SaveData(mContext);
        error_koneksi = mContext.getString(R.string.error_koneksi);
    }


    public void getRequest(final VolleyCallback callback, String URL) {
        cd.show_p_Dialog();
        String tag_string_req = "getTahapan";
        StringRequest getRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        cd.hide_p_Dialog();
                        Log.d("Response", response);
                        try {
                            JSONObject jObj = new JSONObject(response);
                            String data = jObj.getString("data");
                            callback.onSuccess(data);


                        } catch (final JSONException e) {
                            e.printStackTrace();
                            cd.oneButtonDialog(error_koneksi,R.drawable.ic_error_con,R.color.orange_800);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        cd.hide_p_Dialog();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        getInstance().addToRequestQueue(getRequest, tag_string_req);
    }

}
