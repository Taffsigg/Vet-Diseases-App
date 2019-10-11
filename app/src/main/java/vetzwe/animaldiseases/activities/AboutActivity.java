package vetzwe.animaldiseases.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vetzwe.animaldiseases.BuildConfig;
import vetzwe.animaldiseases.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setupToolbar();

        TextView tv_app_ver = findViewById(R.id.tv_app_ver);
        tv_app_ver.setText("version " + BuildConfig.VERSION_CODE + " build " + BuildConfig
                .VERSION_NAME);

        Button btnabt = findViewById(R.id.abtBtn);
        btnabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String infostring = getString(R.string.licenses);

                showLicensesDialog(infostring);
            }
        });
    }

    private void showLicensesDialog(String infostring) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("INFO")
                .setMessage(infostring)
                .setCancelable(true)
                .setPositiveButton("OK, GOT IT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog alert = builder.create();

        alert.show();
    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);

        tvactionbar.setText("Disease");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
