package vetzwe.animaldiseases;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Utils {

    public static Bitmap getBitmapFromAssets(Context ctx, String fileName) {

        AssetManager assetManager = ctx.getAssets();

        InputStream istr;
        try {
            istr = assetManager.open(fileName);

            Bitmap bitmap = BitmapFactory.decodeStream(istr);

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void copyDatabaseFromAssets(Context context, String assetPath, String dbName)
            throws
            SQLiteAssetHelper
                    .SQLiteAssetException {
        Log.w("QuizDb", "copying database from assets...");
        String path = assetPath;
        String dest = context.getApplicationInfo().dataDir + "/databases" + "/" + dbName;
        boolean isZip = false;

        InputStream is;
        try {
            is = context.getAssets().open(path);
        } catch (IOException var12) {
            try {
                is = context.getAssets().open(path + ".zip");
                isZip = true;
            } catch (IOException var11) {
                try {
                    is = context.getAssets().open(path + ".gz");
                } catch (IOException var10) {
                    SQLiteAssetHelper.SQLiteAssetException se = new SQLiteAssetHelper.SQLiteAssetException("Missing " + assetPath + " file (or .zip, .gz archive) in assets, or target folder not writable");
                    se.setStackTrace(var10.getStackTrace());
                    throw se;
                }
            }
        }

        try {
            File f = new File(context.getApplicationInfo().dataDir + "/databases" + "/");
            if (!f.exists()) {
                f.mkdir();
            }

            if (isZip) {
                ZipInputStream zis = getFileFromZip(is);
                if (zis == null) {
                    throw new SQLiteAssetHelper.SQLiteAssetException("Archive is missing a SQLite database file");
                }

                Utils.writeExtractedFileToDisk(zis, new FileOutputStream(dest));
            } else {
                writeExtractedFileToDisk(is, new FileOutputStream(dest));
            }

            Log.w("QuizDb", "database copy complete");
        } catch (IOException var9) {
            SQLiteAssetHelper.SQLiteAssetException se = new SQLiteAssetHelper.SQLiteAssetException("Unable to write " + dest + " to data directory");
            se.setStackTrace(var9.getStackTrace());
            throw se;
        }
    }

    private static void writeExtractedFileToDisk(InputStream in, OutputStream outs) throws IOException {
        byte[] buffer = new byte[1024];

        int length;
        while ((length = in.read(buffer)) > 0) {
            outs.write(buffer, 0, length);
        }

        outs.flush();
        outs.close();
        in.close();
    }

    private static ZipInputStream getFileFromZip(InputStream zipFileStream) throws IOException {
        ZipInputStream zis = new ZipInputStream(zipFileStream);
        ZipEntry ze;
        if ((ze = zis.getNextEntry()) != null) {
            Log.w("QuizDb", "extracting file: '" + ze.getName() + "'...");
            return zis;
        } else {
            return null;
        }
    }

}
