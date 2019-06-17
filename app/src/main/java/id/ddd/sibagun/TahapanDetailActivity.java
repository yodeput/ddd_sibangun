package id.ddd.sibagun;


import android.content.Intent;
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
import id.ddd.sibagun.adapter.TahapanDetailAdapter;
import id.ddd.sibagun.model.TahapanDetail;
import id.ddd.sibagun.util.CustomDialog;
import id.ddd.sibagun.util.GetData;
import id.ddd.sibagun.util.PrefManager;
import id.ddd.sibagun.util.VolleyCallback;

import static id.ddd.sibagun.util.URLConfig.TAHAPAN;
import static id.ddd.sibagun.util.URLConfig.TAHAPAN_DETAIL;

public class TahapanDetailActivity extends AppCompatActivity implements TahapanDetailAdapter.TahapanDetailAdapterListener {

    private PrefManager pref;
    private CustomDialog cd;
    private GetData getData;
    private VolleyCallback vc;

    private TahapanDetailAdapter mAdapter;
    private List<TahapanDetail> tahapanDetail;

    private String parameter,title;
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
        ini_data();
        init_view();

    }

    private void ini_data() {
        Intent i = getIntent();
        Bundle b = i.getExtras();
        parameter = b.getString("parameter");
        title = b.getString("output");
        tahapanDetail = new ArrayList<>();
        mAdapter = new TahapanDetailAdapter(this,tahapanDetail,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        getData.getRequest(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                List<TahapanDetail> items = new Gson().fromJson(result, new TypeToken<List<TahapanDetail>>() {}.getType());
                tahapanDetail.clear();
                tahapanDetail.addAll(items);
                Log.e("wwkwkwkkwkw",""+items.size());
                mAdapter.notifyDataSetChanged();
            }
        }, TAHAPAN_DETAIL + parameter);


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
            ab.setTitle(title);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    public void onTahapanDetailSelected(TahapanDetailAdapter tahapan) {

    }
}

