package id.ddd.sibagun.adapter;

import android.content.Context;
import android.graphics.Color;
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
import id.ddd.sibagun.model.TahapanDetail;


public class TahapanDetailAdapter extends RecyclerView.Adapter<TahapanDetailAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<TahapanDetail> tahapanDetail;
    private List<TahapanDetail> tahapanDetailFiltered;
    private TahapanDetailAdapterListener listener;


    public TahapanDetailAdapter(Context context, List<TahapanDetail> tahapanDetail, TahapanDetailAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.tahapanDetail = tahapanDetail;
        this.tahapanDetailFiltered = tahapanDetail;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tahapan_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView txt_nm_opd, txt_target, txt_selesai, txt_status;
        public CardView cv_data;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            txt_nm_opd = view.findViewById(R.id.txt_nm_opd);
            txt_target = view.findViewById(R.id.txt_target);
            txt_selesai = view.findViewById(R.id.txt_selesai);
            txt_status = view.findViewById(R.id.txt_status);
            cv_data = view.findViewById(R.id.cv_data);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //listener.onTahapanDetailSelected(tahapanDetailFiltered.get(getAdapterPosition()));
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TahapanDetail tahapan = tahapanDetailFiltered.get(position);
        String status = tahapan.getStatus();
        holder.txt_nm_opd.setText(tahapan.getUser());
        holder.txt_target.setText(tahapan.getTgl_Target());
        holder.txt_selesai.setText(tahapan.getTgl_Target());

        if (status.equals("1")) {
            holder.txt_status.setText("FINAL");
            holder.cv_data.setCardBackgroundColor(Color.parseColor("#a5d6a7"));

        }
        if (status.equals("0")) {
            holder.txt_status.setText("DRAFT");
            holder.cv_data.setCardBackgroundColor(Color.parseColor("#ef9a9a"));
        }

    }


    @Override
    public int getItemCount() {
        return tahapanDetailFiltered.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    tahapanDetailFiltered = tahapanDetail;
                } else {
                    List<TahapanDetail> filteredList = new ArrayList<>();
                    for (TahapanDetail row : tahapanDetail) {

                        if ((row.getDokumen().toString().contains(charString.toLowerCase()))) {
                            filteredList.add(row);
                        }

                    }

                    tahapanDetailFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tahapanDetailFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tahapanDetailFiltered = (ArrayList<TahapanDetail>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }


    public interface TahapanDetailAdapterListener {
        void onTahapanDetailSelected(TahapanDetailAdapter tahapan);
    }


}