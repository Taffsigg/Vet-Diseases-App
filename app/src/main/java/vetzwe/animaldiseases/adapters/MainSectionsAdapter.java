package vetzwe.animaldiseases.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.Utils;

public class MainSectionsAdapter extends RecyclerView.Adapter<MainSectionsAdapter.ViewHolder> {

    private final Context context;

    private final List<String> activityNames;

    private View view1;
    private ViewHolder viewHolder1;

    private ILaunchActivity iLaunchActivity;

    public MainSectionsAdapter(Context context, List<String> activityNames, ILaunchActivity iLaunchActivity) {

        this.context = context;
        this.activityNames = activityNames;

        this.iLaunchActivity = iLaunchActivity;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public CardView card;

        public ImageView image;

        public ViewHolder(View v) {

            super(v);

            title = v.findViewById(R.id.title);
            card = v.findViewById(R.id.card);
            image = v.findViewById(R.id.image);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.main_section_item, parent, false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (position % 2 == 0) {
            holder.card.setBackgroundColor(context.getResources().getColor(R.color
                    .colorPrimaryDark));

        } else {
            holder.card.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }

        holder.title.setText(activityNames.get(position));

        holder.image.setImageBitmap(Utils.getBitmapFromAssets(context, "icons/" + activityNames.get
                (position) + ".png"));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedItem = activityNames.get(position);
                iLaunchActivity.launchActivity(selectedItem);
            }
        });
    }

    @Override
    public int getItemCount() {

        return activityNames.size();
    }
}
