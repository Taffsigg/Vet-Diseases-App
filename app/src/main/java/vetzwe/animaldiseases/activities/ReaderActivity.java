package vetzwe.animaldiseases.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.databases.DatabaseQuery;
import vetzwe.animaldiseases.entities.Disease;
import vetzwe.animaldiseases.entities.DiseaseInfo;
import vetzwe.animaldiseases.entities.GalleryItem;
import vetzwe.animaldiseases.prefs.UserPrefs;

public class ReaderActivity extends AppCompatActivity {

    private int id;

    private Disease disease;

    private DiseaseInfo diseaseInfo;

    private TextView title1, description, symptoms, prevention, treatment;

    private UserPrefs userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        userPrefs = new UserPrefs(this);

        setupToolbar();

        id = getIntent().getIntExtra("id", 1);

        disease = new DatabaseQuery(this).fetchDisease(id);

        diseaseInfo = new DatabaseQuery(this).fetchDiseaseInfo(id);

        title1 = findViewById(R.id.title1);
        description = findViewById(R.id.description);
        symptoms = findViewById(R.id.symptoms);
        prevention = findViewById(R.id.prevention);
        treatment = findViewById(R.id.treatment);

        title1.setTextColor(userPrefs.getColor());

        description.setTextColor(userPrefs.getColor());
        symptoms.setTextColor(userPrefs.getColor());
        prevention.setTextColor(userPrefs.getColor());
        treatment.setTextColor(userPrefs.getColor());

        title1.setText(disease.getDisease());

        description.setText(diseaseInfo.getDescription());
        symptoms.setText(diseaseInfo.getSymptoms());
        prevention.setText(diseaseInfo.getPrevention());
        treatment.setText(diseaseInfo.getTreatment());

        Button btn_images = findViewById(R.id.btn_images);
        btn_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<GalleryItem> galleryItems = new DatabaseQuery(ReaderActivity.this)
                        .getImages(id);

                if (galleryItems.size() > 0) {
                    Intent intent = new Intent(ReaderActivity.this, GalleryActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                } else {
                    showToast("No images found");
                }

            }
        });
    }

    private void showToast(String toastText) {
        Toast _toast = Toast.makeText(ReaderActivity.this, toastText, Toast.LENGTH_SHORT);
        _toast.setGravity(Gravity.CENTER, 0, 0);
        _toast.show();
    }

    private void setupToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        TextView tvactionbar = mToolbar.findViewById(R.id.title);
        tvactionbar.setTextColor(userPrefs.getColor());

        tvactionbar.setText("Disease");
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
