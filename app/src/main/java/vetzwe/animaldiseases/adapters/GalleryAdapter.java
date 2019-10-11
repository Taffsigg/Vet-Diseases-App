package vetzwe.animaldiseases.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.Utils;
import vetzwe.animaldiseases.entities.GalleryItem;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final Context context;

    private List<GalleryItem> galleryItems;

    private View view1;
    private GalleryAdapter.ViewHolder viewHolder1;

    public GalleryAdapter(Context context, List<GalleryItem> galleryItems) {

        this.context = context;
        this.galleryItems = galleryItems;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public ImageView imageView;

        public ViewHolder(View v) {

            super(v);

            title = v.findViewById(R.id.title);

            imageView = v.findViewById(R.id.imageview);
        }
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false);

        viewHolder1 = new GalleryAdapter.ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, final int position) {

        holder.title.setText(galleryItems.get(position).getDescription());

        holder.imageView.setImageBitmap(Utils.getBitmapFromAssets(context, "images/" +
                galleryItems.get(position).getImageName()));
    }

    @Override
    public int getItemCount() {

        return galleryItems.size();
    }
}