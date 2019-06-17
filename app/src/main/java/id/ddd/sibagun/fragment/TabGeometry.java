package id.ddd.sibagun.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import id.ddd.sibagun.R;


/**
 * Created by Admin on 5/25/2016.
 */
public class TabGeometry extends Fragment {

    EditText side;
    TextView tv_vol, tv_luas, tv_kel;
    Button hasil;

    double vol, luas, kel;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.geometry_cube, container, false);
        hasil = (Button) v.findViewById(R.id.has);


        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

