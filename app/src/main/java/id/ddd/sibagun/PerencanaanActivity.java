package id.ddd.sibagun;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ddd.sibagun.adapter.TahapanAdapter;
import id.ddd.sibagun.model.TahapanItem;
import id.ddd.sibagun.util.CustomDialog;
import id.ddd.sibagun.util.GetData;
import id.ddd.sibagun.util.PrefManager;
import id.ddd.sibagun.util.VolleyCallback;

import static id.ddd.sibagun.util.URLConfig.TAHAPAN;

public class PerencanaanActivity extends AppCompatActivity implements TahapanAdapter.TahapanAdapterListener {

    private PrefManager pref;
    private CustomDialog cd;
    private GetData getData;
    private VolleyCallback vc;

    private TahapanAdapter mAdapter;
    private List<TahapanItem> tahapanItem;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perencaan);
        ButterKnife.bind(this);
        init_class();
        init_view();
        ini_data();


    }

    private void ini_data() {
        tahapanItem = new ArrayList<>();
        mAdapter = new TahapanAdapter(this, tahapanItem, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getData.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                List<TahapanItem> items = new Gson().fromJson(result, new TypeToken<List<TahapanItem>>() {
                }.getType());
                tahapanItem.clear();
                tahapanItem.addAll(items);
                mAdapter.notifyDataSetChanged();
            }
        }, TAHAPAN + "2020");


    }

    private void init_class() {
        pref = new PrefManager(this);
        cd = new CustomDialog(this);
        getData = new GetData(this);

    }

    private void init_view() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Perencanaan");
            ab.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PerencanaanActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        overridePendingTransition(R.anim.anim_back_slide_in_right, R.anim.anim_back_slide_out_left);

    }


    @Override
    public void onTahapanSelected(TahapanItem tahapan) {
        String data_final = tahapan.getDataFinal();
        String data_draft = tahapan.getDataDraft();
        if((data_draft.equals("0"))&&(data_final.equals("0"))){
           cd.toastWarning("Data tidak tersedia");
        } else {
            String param = tahapan.getIdKel() + "." + tahapan.getIdTahapan() + "." + tahapan.getTahun() + "." + tahapan.getTglTarget() + "." + tahapan.getUraian();
            Intent i = new Intent(this, TahapanDetailActivity.class);
            i.putExtra("parameter", param);
            i.putExtra("output", tahapan.getUraian());
            startActivity(i);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
        }
    }

}

