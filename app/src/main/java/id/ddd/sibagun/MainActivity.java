package id.ddd.sibagun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ddd.sibagun.fragment.HomeFragment;
import id.ddd.sibagun.fragment.TabGeoCone;
import id.ddd.sibagun.fragment.VolumeFragment;
import id.ddd.sibagun.util.CustomDialog;
import id.ddd.sibagun.util.PostData;
import id.ddd.sibagun.util.PrefManager;

import static id.ddd.sibagun.util.DDD.getInstance;


public class MainActivity extends AppCompatActivity {

    private PrefManager pref;
    private CustomDialog cd;
    private PostData postData;
    private String username, lastupdate;
    private Handler handler;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.bt_logout)
    ImageView but_logout;
    @BindView(R.id.txt_username)
    AppCompatTextView txt_username;
    @BindView(R.id.txt_lastupdate)
    AppCompatTextView txt_lastupdate;
    @BindView(R.id.rl_akun)
    RelativeLayout rl_akun;
    @BindView(R.id.rl_login)
    RelativeLayout rl_login;

    @OnClick(R.id.bt_logout)
    void logout() {
        cd.dialog_logout();
    }

    @OnClick(R.id.bt_settings)
    void bt_settings() {
        cd.toastWarning("Layanan Belum Tersedia");
        //startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        //finish();
        //overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        //overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
    }


    @OnClick(R.id.but_login)
    void but_login() {
        cd.show_p_Dialog();
        handler.postDelayed(new Runnable() {
            public void run() {
                cd.hide_p_Dialog();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();
            }
        }, 1000);

    }

    @OnClick(R.id.rl_perencanaan)
    void perencaaan() {
        startActivity(new Intent(MainActivity.this, PerencanaanActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @OnClick(R.id.rl_penganggaran)
    void penganggaran() {
        cd.toastWarning("Layanan Belum Tersedia");
    }

    @OnClick(R.id.rl_monitoring)
    void monitoring() {
        cd.toastWarning("Layanan Belum Tersedia");
    }

    @OnClick(R.id.rl_pelaporan)
    void pelaporan() {
        cd.toastWarning("Layanan Belum Tersedia");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init_class();
        init_view();
        handler = new Handler();
    }

    private void init_class() {
        pref = new PrefManager(MainActivity.this);
        cd = new CustomDialog(MainActivity.this);
        postData = new PostData(MainActivity.this);
    }

    private void init_view() {
        rl_akun.setVisibility(View.GONE);
        if (pref.isLoggedIn()) {
            rl_akun.setVisibility(View.VISIBLE);
            rl_login.setVisibility(View.GONE);

            username = pref.getPref("SESS_NAMA");
            lastupdate = pref.getPref("last_update");

            txt_username.setText(username);
            txt_lastupdate.setText("Last Update: " + lastupdate);
            but_logout.setVisibility(View.VISIBLE);
        }
        setSupportActionBar(mToolbar);

    }

}