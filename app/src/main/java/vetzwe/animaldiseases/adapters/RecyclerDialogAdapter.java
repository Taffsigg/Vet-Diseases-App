package vetzwe.animaldiseases.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vetzwe.animaldiseases.R;

/**
 * Custom RecyclerView in a Dialog Adapter. It is used to display a long selection of items in a.......doh
 */

public class RecyclerDialogAdapter extends RecyclerView.Adapter<RecyclerDialogAdapter.ViewHolder> {

    private Context context;

    private View view1;
    private ViewHolder viewHolder1;

    private RecyclerDialogFragment dialogFragment;

    private List<String> phones;

    private ILaunchActivity iLaunchActivity;

    public RecyclerDialogAdapter(Context context, RecyclerDialogFragment dialogFragment,
                                 List<String> phones, ILaunchActivity iLaunchActivity) {

        this.context = context;
        this.dialogFragment = dialogFragment;

        this.iLaunchActivity = iLaunchActivity;

        this.phones = phones;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.province.setText(phones.get(position));

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLaunchActivity.launchActivity(phones.get(position));
                dialogFragment.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {

        return phones.size();
    }
}
