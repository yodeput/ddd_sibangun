package id.ddd.sibagun;


import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ddd.sibagun.util.CustomDialog;
import id.ddd.sibagun.util.PostData;
import id.ddd.sibagun.util.PrefManager;

public class LoginActivity extends AppCompatActivity {

    private PrefManager pref;
    private CustomDialog cd;
    private PostData postData;
    @BindView(R.id.edit_username)
    TextInputEditText edit_username;
    @BindView(R.id.edit_password)
    TextInputEditText edit_password;
    @BindView(R.id.but_signin)
    Button but_signin;

    @OnClick(R.id.but_signin)
    void signin() {
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            cd.toastWarning(getString(R.string.error_form_login));
        } else {
            postData.login(username,password);
        }
    }

    @OnClick(R.id.but_skip_login)
    void skip_signin() {
        //skip_login();
        cd.dialog_skip_login();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init_class();
        startActivity(new Intent(this, MainActivity.class));
        finish();
        /*
        if (pref.isLoggedIn() && !pref.skipLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else if (!pref.isLoggedIn() && pref.skipLoggedIn()) {

        }
        */
    }

    private void init_class(){
        pref = new PrefManager(this);
        cd = new CustomDialog(this);
        postData = new PostData(this);
    }

}

