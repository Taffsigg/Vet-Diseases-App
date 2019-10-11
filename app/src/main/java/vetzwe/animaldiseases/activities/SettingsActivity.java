package vetzwe.animaldiseases.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.adapters.ILaunchActivity;
import vetzwe.animaldiseases.adapters.TextRecyclerAdapter;
import vetzwe.animaldiseases.prefs.UserPrefs;

public class SettingsActivity extends AppCompatActivity implements ILaunchActivity {

    private UserPrefs userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userPrefs = new UserPrefs(this);

        setupToolbar();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recylerViewLayoutManager);

        final TextRecyclerAdapter adapter = new TextRecyclerAdapter(this, Arrays.asList(getResources()
                .getStringArray(R.array.settings)), this);
        recyclerView.setAdapter(adapter);

    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText("Settings");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    private void showLanguageDialog() {
        final Dialog dialog = new Dialog(SettingsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.language_dialog);
        dialog.setCancelable(true);

        final RadioButton radio_en = dialog.findViewById(R.id.radio_en);
        final RadioButton radio_nde = dialog.findViewById(R.id.radio_nde);
        final RadioButton radio_sn = dialog.findViewById(R.id.radio_sn);

        AppCompatButton btn_yes = dialog.findViewById(R.id.btn_yes);
        AppCompatButton btn_no = dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio_en.isChecked()) {
                    userPrefs.saveLanguage(getString(R.string.lang_en));
                    dialog.dismiss();

                } else if (radio_sn.isChecked()) {
                    userPrefs.saveLanguage(getString(R.string.lang_sn));
                    dialog.dismiss();

                } else if (radio_nde.isChecked()) {
                    userPrefs.saveLanguage(getString(R.string.lang_nde));
                    dialog.dismiss();

                } else {
                    showToast("Please select an option");
                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showColorDialog() {
        final Dialog dialog = new Dialog(SettingsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.color_dialog);
        dialog.setCancelable(true);

        final RadioButton radio_red = dialog.findViewById(R.id.radio_red);
        final RadioButton radio_default = dialog.findViewById(R.id.radio_default);
        final RadioButton radio_green = dialog.findViewById(R.id.radio_green);
        final RadioButton radio_blue = dialog.findViewById(R.id.radio_blue);
        final RadioButton radio_black = dialog.findViewById(R.id.radio_black);

        AppCompatButton btn_yes = dialog.findViewById(R.id.btn_yes);
        AppCompatButton btn_no = dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radio_red.isChecked()) {
                    userPrefs.saveColor(UserPrefs.RED);
                    dialog.dismiss();

                } else if (radio_default.isChecked()) {
                    userPrefs.saveColor(getResources().getColor(R.color.colorPrimaryDark));
                    dialog.dismiss();

                } else if (radio_green.isChecked()) {
                    userPrefs.saveColor(UserPrefs.GREEN);
                    dialog.dismiss();

                } else if (radio_blue.isChecked()) {
                    userPrefs.saveColor(UserPrefs.BLUE);
                    dialog.dismiss();

                } else if (radio_black.isChecked()) {
                    userPrefs.saveColor(UserPrefs.BLACK);
                    dialog.dismiss();

                } else {
                    showToast("Please select an option");
                }
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showToast(String toastText) {
        Toast _toast = Toast.makeText(SettingsActivity.this, toastText, Toast.LENGTH_SHORT);
        _toast.setGravity(Gravity.CENTER, 0, 0);
        _toast.show();
    }

    @Override
    public void launchActivity(String selectedItem) {
        if (selectedItem.toLowerCase().equals("language")) {
            showLanguageDialog();

        } else if (selectedItem.toLowerCase().equals("text color")) {
            showColorDialog();
        }
    }

    @Override
    public void launchActivity(int id) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}