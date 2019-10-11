package vetzwe.animaldiseases.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import vetzwe.animaldiseases.R;

public class UserPrefs {

    private Context context;
    private SharedPreferences prefs;

    public static int BLACK = Color.BLACK;
    public static int BLUE = Color.BLUE;
    public static int RED = Color.RED;
    public static int GREEN = Color.GREEN;

    public UserPrefs(Context paramContext) {
        this.context = paramContext;
        this.prefs = paramContext.getSharedPreferences("user_prefs", 0);

    }

    public void clearAllSubscriptions() {
        this.prefs.edit().clear().apply();
    }

    public int getCurrentLaunchCount() {
        return this.prefs.getInt("launch_count", 0);
    }

    public boolean isFirstLaunch() {
        return getCurrentLaunchCount() <= 0;
    }

    public void saveCurrentLaunchCount(int paramInt) {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putInt("launch_count", paramInt);
        localEditor.apply();
    }

    public void saveReaderCount(int paramInt) {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putInt("reader_count", paramInt);
        localEditor.apply();
    }

    public int getReaderCount() {
        return this.prefs.getInt("reader_count", 0);
    }

    public void savePassageHelpShown() {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putString("passage_help_shown", "yes");
        localEditor.apply();
    }

    public void saveTextSize(int size) {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putInt("text_size", size);
        localEditor.apply();
    }

    public int getReaderTextSize() {
        return this.prefs.getInt("text_size", 14);
    }

    public void saveIntroShown() {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putString("intro_shown", "yes");
        localEditor.apply();
    }

    public boolean isIntroShown() {
        String ans = this.prefs.getString("intro_shown", "no");
        if (ans.equals("yes")) {
            return true;
        }
        return false;
    }

    public String getLanguage() {
        return this.prefs.getString("language", "en");
    }

    public void saveLanguage(String lang) {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putString("language", lang);
        localEditor.apply();
    }

    public int getColor() {
        return this.prefs.getInt("color", context.getResources().getColor(R.color.colorPrimaryDark));
    }

    public void saveColor(int color) {
        SharedPreferences.Editor localEditor = this.prefs.edit();
        localEditor.putInt("color", color);
        localEditor.apply();
    }

}
