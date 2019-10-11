package vetzwe.animaldiseases.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Arrays;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.Utils;
import vetzwe.animaldiseases.adapters.ILaunchActivity;
import vetzwe.animaldiseases.adapters.MainSectionsAdapter;
import vetzwe.animaldiseases.prefs.UserPrefs;

public class MainActivity extends AppCompatActivity implements ILaunchActivity {

    private UserPrefs userPrefs;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        userPrefs = new UserPrefs(MainActivity.this);
        userPrefs.saveCurrentLaunchCount(userPrefs.getCurrentLaunchCount());

        setupToolbar();

        new CopyDb().execute();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        MainSectionsAdapter adapter = new MainSectionsAdapter(this, Arrays.asList(getResources()
                .getStringArray(R.array.main_sections)), this);
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText(getString(R.string.app_name));
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    /**
     * Display Progress bar while Logging in
     */
    private void displayLoader() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Copying Data...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    public void launchActivity(String selectedItem) {
        if (selectedItem.toLowerCase().equals("diseases")) {
            Intent intent = new Intent(MainActivity.this, DiseasesActivity.class);
            startActivity(intent);

        } else if (selectedItem.toLowerCase().equals("about app")) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (selectedItem.toLowerCase().equals("contact us")) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);

        } else if (selectedItem.toLowerCase().equals("settings")) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void launchActivity(int id) {

    }

    class CopyDb extends AsyncTask {

        @Override
        protected void onPreExecute() {
            displayLoader();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            /**
             * each time the app is launched, re-copy the main medications database from the Assets
             * folder to incorporate any new App updates that may have been introduced
             * */
            Utils.copyDatabaseFromAssets(getApplicationContext(), "databases/data.db", "data.db");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            pDialog.dismiss();
        }
    }
}
