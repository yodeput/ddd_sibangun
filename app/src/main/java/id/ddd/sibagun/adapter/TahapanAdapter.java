package id.ddd.sibagun.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import id.ddd.sibagun.R;
import id.ddd.sibagun.TahapanDetailActivity;
import id.ddd.sibagun.model.TahapanItem;


public class TahapanAdapter extends RecyclerView.Adapter<TahapanAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<TahapanItem> tahapanItem;
    private List<TahapanItem> tahapanItemFiltered;
    private TahapanAdapterListener listener;



    public TahapanAdapter(Context context, List<TahapanItem> tahapanItem, TahapanAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.tahapanItem = tahapanItem;
        this.tahapanItemFiltered = tahapanItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tahapan, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_target, txt_number, txt_uraian,txt_output,txt_status,txt_data;
        public CardView cv_item,cv_data;
        public ImageView ck_1,ck_2;

        public MyViewHolder(View view) {
            super(view);
            txt_target = view.findViewById(R.id.txt_target);
            txt_number = view.findViewById(R.id.txt_number);
            txt_uraian = view.findViewById(R.id.txt_uraian);
            txt_output = view.findViewById(R.id.txt_output);
            txt_status = view.findViewById(R.id.txt_status);
            txt_data = view.findViewById(R.id.txt_data);
            cv_item = view.findViewById(R.id.cv_item);
            cv_data = view.findViewById(R.id.cv_data);
            ck_1 = view.findViewById(R.id.ck_1);
            ck_2 = view.findViewById(R.id.ck_2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onTahapanSelected(tahapanItemFiltered.get(getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TahapanItem tahapan = tahapanItemFiltered.get(position);
        String Uraian = tahapan.getUraian();
        String Id_Tahapan = tahapan.getIdTahapan();
        String Tahun = tahapan.getTahun();
        String Tgl_Target = tahapan.getTglTarget();
        String output = tahapan.getOutput();
        String data_final = tahapan.getDataFinal();
        String data_draft = tahapan.getDataDraft();

        holder.txt_uraian.setText(Uraian);
        holder.txt_target.setText(Tgl_Target);
        holder.txt_output.setText(output);
        holder.txt_number.setText(Id_Tahapan);

        if(!data_final.equals("0")){
            holder.txt_status.setText("FINAL");
            holder.txt_data.setText(data_final);
            holder.cv_data.setCardBackgroundColor(Color.parseColor("#a5d6a7"));
            holder.ck_1.setColorFilter(Color.parseColor("#005b9f"));
            holder.ck_2.setColorFilter(Color.parseColor("#0288d1"));
        }
        if(!data_draft.equals("0")){
            holder.txt_status.setText("DRAFT");
            holder.txt_data.setText(data_draft);
            holder.cv_data.setCardBackgroundColor(Color.parseColor("#ef9a9a"));
            holder.ck_1.setColorFilter(Color.parseColor("#9a0007"));
            holder.ck_2.setColorFilter(Color.parseColor("#d32f2f"));
        }

        if((data_draft.equals("0"))&&(data_final.equals("0"))){
            holder.txt_status.setText("");
            holder.txt_data.setText("-");
            holder.cv_data.setCardBackgroundColor(Color.parseColor("#b0bec5"));
            holder.ck_1.setColorFilter(Color.parseColor("#373737"));
            holder.ck_2.setColorFilter(Color.parseColor("#616161"));
        }
    }

   
    @Override
    public int getItemCount() {
        return tahapanItemFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tahapanItemFiltered = tahapanItem;
                } else {
                    List<TahapanItem> filteredList = new ArrayList<>();
                    for (TahapanItem row : tahapanItem) {

                        if ((row.getUraian().toLowerCase().contains(charString.toLowerCase()))) {
                            filteredList.add(row);
                        }

                    }

                    tahapanItemFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tahapanItemFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tahapanItemFiltered = (ArrayList<TahapanItem>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }

    public interface TahapanAdapterListener {
        void onTahapanSelected(TahapanItem tahapan);
    }

}