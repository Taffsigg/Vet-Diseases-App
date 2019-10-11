package vetzwe.animaldiseases.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.adapters.DiseasesAdapter;
import vetzwe.animaldiseases.adapters.ILaunchActivity;
import vetzwe.animaldiseases.databases.DatabaseQuery;
import vetzwe.animaldiseases.entities.Disease;

public class DiseasesActivity extends AppCompatActivity implements ILaunchActivity {

    private EditText edSearch;

    private DiseasesAdapter adapter = null;

    private List<Disease> diseases;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_search);

        setupToolbar();

        diseases = new DatabaseQuery(DiseasesActivity.this).fetchAllDiseases();

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager
                (DiseasesActivity.this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        adapter = new DiseasesAdapter(DiseasesActivity.this, diseases, this);
        recyclerView.setAdapter(adapter);

        edSearch = findViewById(R.id.edSearch);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText("Disease");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void launchActivity(String selectedItem) {

    }

    @Override
    public void launchActivity(int id) {
        Intent intent = new Intent(DiseasesActivity.this, ReaderActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
