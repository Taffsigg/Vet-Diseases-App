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

public class TextRecyclerAdapter extends RecyclerView.Adapter<TextRecyclerAdapter.ViewHolder> implements Filterable {

    private final Context context;

    private List<String> diseases;
    private List<String> diseasesFiltered;

    private View view1;
    private ViewHolder viewHolder1;

    private ILaunchActivity iLaunchActivity;

    public TextRecyclerAdapter(Context context, List<String> diseases, ILaunchActivity iLaunchActivity) {

        this.context = context;
        this.diseases = diseases;

        this.iLaunchActivity = iLaunchActivity;

        this.diseasesFiltered = diseases;
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

        holder.title.setText(diseasesFiltered.get(position));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = diseasesFiltered.get(position);
                iLaunchActivity.launchActivity(selectedItem);
            }
        });
    }

    @Override
    public int getItemCount() {

        return diseasesFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    diseasesFiltered = diseases;
                } else {

                    List<String> filteredList = new ArrayList<>();

                    for (String _str : diseases) {

                        if (_str.toLowerCase().contains(charString.toLowerCase())) {

                            filteredList.add(_str);
                        }
                    }

                    diseasesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = diseasesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                diseasesFiltered = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}