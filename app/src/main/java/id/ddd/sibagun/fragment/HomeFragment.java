package id.ddd.sibagun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ddd.sibagun.LoginActivity;
import id.ddd.sibagun.R;
import id.ddd.sibagun.util.CustomDialog;
import id.ddd.sibagun.util.PostData;
import id.ddd.sibagun.util.PrefManager;


public class HomeFragment extends Fragment {

    private PrefManager pref;
    private CustomDialog cd;
    private PostData postData;
    private String username, lastupdate;
    private Handler handler;
    @BindView(R.id.txt_username)
    AppCompatTextView txt_username;
    @BindView(R.id.txt_lastupdate)
    AppCompatTextView txt_lastupdate;

    @BindView(R.id.rl_akun)
    RelativeLayout rl_akun;
    @BindView(R.id.rl_login)
    RelativeLayout rl_login;

    @OnClick(R.id.but_login)
    void but_login() {
        cd.show_p_Dialog();
        handler.postDelayed(new Runnable() {
            public void run() {
                cd.hide_p_Dialog();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        }, 1000);

    }

    @OnClick(R.id.rl_perencanaan) void perencaaan(){

    }
    @OnClick(R.id.rl_penganggaran) void penganggaran(){

    }
    @OnClick(R.id.rl_monitoring) void monitoring(){

    }
    @OnClick(R.id.rl_pelaporan) void pelaporan(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);

        init_class();
        init_view();
        handler = new Handler();

        return v;
    }


    private void init_class(){
        pref = new PrefManager(getActivity());
        cd = new CustomDialog(getActivity());
        postData = new PostData(getActivity());
    }

    private void init_view(){
        rl_akun.setVisibility(View.GONE);
        if (pref.isLoggedIn()) {
            rl_akun.setVisibility(View.VISIBLE);
            rl_login.setVisibility(View.GONE);

            username = pref.getPref("SESS_NAMA");
            lastupdate = pref.getPref("last_update");

            txt_username.setText(username);
            txt_lastupdate.setText("Last Update: " + lastupdate);
        }

    }
}

