package vetzwe.animaldiseases.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.adapters.GalleryAdapter;
import vetzwe.animaldiseases.databases.DatabaseQuery;
import vetzwe.animaldiseases.entities.GalleryItem;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        setupToolbar();

        int id = getIntent().getIntExtra("id", 1);

        List<GalleryItem> galleryItems = new DatabaseQuery(this).getImages(id);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        GalleryAdapter adapter = new GalleryAdapter(this, galleryItems);
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText(getString(R.string.app_name));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
