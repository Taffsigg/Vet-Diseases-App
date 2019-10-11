package vetzwe.animaldiseases.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.entities.ProvinceItem;

public class ProvinceItemAdapter extends RecyclerView.Adapter<ProvinceItemAdapter.ViewHolder> {

    private final Context context;

    private List<ProvinceItem> provinces;

    private View view1;
    private ViewHolder viewHolder1;

    private ILaunchActivity iLaunchActivity;

    public ProvinceItemAdapter(Context context, List<ProvinceItem> provinces, ILaunchActivity iLaunchActivity) {

        this.context = context;
        this.provinces = provinces;

        this.iLaunchActivity = iLaunchActivity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView province;

        public CardView card;

        public ViewHolder(View v) {

            super(v);

            province = v.findViewById(R.id.province);

            card = v.findViewById(R.id.card);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.province.setText(provinces.get(position).getProvince());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLaunchActivity.launchActivity(provinces.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {

        return provinces.size();
    }
}