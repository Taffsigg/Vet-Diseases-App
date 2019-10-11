package vetzwe.animaldiseases.databases;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vetzwe.animaldiseases.R;
import vetzwe.animaldiseases.entities.Disease;
import vetzwe.animaldiseases.entities.DiseaseInfo;
import vetzwe.animaldiseases.entities.GalleryItem;
import vetzwe.animaldiseases.entities.ProvinceItem;
import vetzwe.animaldiseases.prefs.UserPrefs;

public class DatabaseQuery extends DatabaseObject {

    public static String TBL_EN = "diseases_en";
    public static String TBL_NDE = "diseases_nde";
    public static String TBL_SN = "diseases_sn";

    public static String IMG_TBL_EN = "images_en";
    public static String IMG_TBL_NDE = "images_nde";
    public static String IMG_TBL_SN = "images_sn";

    private Context context;

    public DatabaseQuery(Context paramContext) {
        super(paramContext);
        context = paramContext;
    }

    public List<Disease> fetchAllDiseases() {
        List<Disease> diseases = new ArrayList<>();

        String query = "SELECT * FROM diseases";
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                Disease disease = new Disease();
                disease.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));
                disease.setDisease(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("disease")));

                diseases.add(disease);

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();
        return diseases;
    }

    public Disease fetchDisease(int id) {
        Disease disease = new Disease();

        String query = "SELECT * FROM diseases WHERE id = " + id;
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                disease.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));
                disease.setDisease(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("disease")));

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();
        return disease;
    }

    public DiseaseInfo fetchDiseaseInfo(int id) {
        String lang = new UserPrefs(context).getLanguage();

        String table = null;

        if (lang.equals(context.getString(R.string.lang_en))) {
            table = TBL_EN;

        } else if (lang.equals(context.getString(R.string.lang_nde))) {
            table = TBL_NDE;

        } else if (lang.equals(context.getString(R.string.lang_sn))) {
            table = TBL_SN;
        }

        DiseaseInfo disease = new DiseaseInfo();

        String query = "SELECT * FROM " + table + " WHERE id=" + id;
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                disease.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));

                disease.setDescription(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("description")));

                disease.setSymptoms(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("symptoms")));

                disease.setPrevention(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("prevention")));

                disease.setTreatment(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("treatment")));

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();
        return disease;
    }

    public List<GalleryItem> getImages(int id) {
        List<GalleryItem> galleryItems = new ArrayList<>();

        String lang = new UserPrefs(context).getLanguage();

        String table = null;

        if (lang.equals(context.getString(R.string.lang_en))) {
            table = IMG_TBL_EN;

        } else if (lang.equals(context.getString(R.string.lang_nde))) {
            table = IMG_TBL_NDE;

        } else if (lang.equals(context.getString(R.string.lang_sn))) {
            table = IMG_TBL_SN;
        }

        String query = "SELECT * FROM " + table + " WHERE disease_id=" + id;
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                GalleryItem galleryItem = new GalleryItem();
                galleryItem.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));

                galleryItem.setDescription(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("description")));

                galleryItem.setImageName(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("image")));

                galleryItems.add(galleryItem);

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();

        return galleryItems;
    }

    public List<ProvinceItem> getProvinces() {
        List<ProvinceItem> provinces = new ArrayList<>();

        String query = "SELECT * FROM provinces";
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                ProvinceItem province = new ProvinceItem();

                province.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));
                province.setProvince(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("province")));

                provinces.add(province);

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();

        return provinces;
    }

    public ProvinceItem getProvince(int id) {
        ProvinceItem province = new ProvinceItem();

        String query = "SELECT * FROM provinces WHERE id=" + id;
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                province.setId(localCursor.getInt(localCursor.getColumnIndexOrThrow("id")));
                province.setProvince(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("province")));

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();

        return province;
    }

    public List<String> getPhones(int id) {
        List<String> phones = new ArrayList<>();

        String query = "SELECT * FROM phones WHERE province=" + id;
        Cursor localCursor = getDbConnection().rawQuery(query, null);
        if (localCursor.moveToFirst()) {
            do {

                phones.add(localCursor.getString(localCursor.getColumnIndexOrThrow
                        ("phone")));

            } while (localCursor.moveToNext());
        }
        localCursor.close();
        closeDbConnection();

        return phones;
    }

}
