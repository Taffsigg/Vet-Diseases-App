package vetzwe.animaldiseases.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.adapters.ILaunchActivity;
import vetzwe.animaldiseases.adapters.ProvinceItemAdapter;
import vetzwe.animaldiseases.adapters.RecyclerDialogFragment;
import vetzwe.animaldiseases.databases.DatabaseQuery;
import vetzwe.animaldiseases.entities.ProvinceItem;

public class ContactActivity extends AppCompatActivity implements ILaunchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        setupToolbar();

        List<ProvinceItem> provinces = new DatabaseQuery(this).getProvinces();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        ProvinceItemAdapter adapter = new ProvinceItemAdapter(this, provinces, this);
        recyclerView.setAdapter(adapter);

    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText("Contact Us");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void launchActivity(String selectedItem) {

    }

    @Override
    public void launchActivity(int id) {
        final FragmentManager fragman = getFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);

        final RecyclerDialogFragment recvwDlg = new RecyclerDialogFragment();
        recvwDlg.setArguments(bundle);

        recvwDlg.show(fragman, "Med_Journal");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
