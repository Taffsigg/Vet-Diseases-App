package vetzwe.animaldiseases.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.entities.Disease;

public class DiseasesAdapter extends RecyclerView.Adapter<DiseasesAdapter.ViewHolder>
        implements
        Filterable {

    private final Context context;

    private List<Disease> diseases;
    private List<Disease> diseaseFiltered;

    private View view1;
    private ViewHolder viewHolder1;

    private ILaunchActivity iLaunchActivity;

    public DiseasesAdapter(Context context, List<Disease> diseases, ILaunchActivity iLaunchActivity) {

        this.context = context;
        this.diseases = diseases;

        this.iLaunchActivity = iLaunchActivity;

        this.diseaseFiltered = diseases;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public CardView card;

        public ViewHolder(View v) {

            super(v);

            title = v.findViewById(R.id.title);

            card = v.findViewById(R.id.card);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.textview_item, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(diseaseFiltered.get(position).getDisease());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = diseaseFiltered.get(position).getId();
                iLaunchActivity.launchActivity(id);
            }
        });
    }

    @Override
    public int getItemCount() {

        return diseaseFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    diseaseFiltered = diseases;
                } else {

                    List<Disease> filteredList = new ArrayList<>();

                    for (Disease _dis : diseases) {

                        if (_dis.getDisease().toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(_dis);
                        }
                    }

                    diseaseFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = diseaseFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                diseaseFiltered = (List<Disease>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}