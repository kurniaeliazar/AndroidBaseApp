package kurniaeliazar.datastorageproject.helper;

import android.content.Context;
import android.content.SharedPreferences;

import kurniaeliazar.datastorageproject.data.HighScoreData;

/**
 * Created by kurniaeliazar on 5/8/17.
 */

public class SharedPreferencesHelper {

    public static final String PREFS_NAME = "HighscoreFile";
    private Context context;
    private SharedPreferences sharedData;

    public SharedPreferencesHelper(Context context) {
        this.context = context;
        sharedData = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void storeHighscoreData(HighScoreData highScoreData) {
        sharedData.edit().putString("username", highScoreData.getUsername()).apply();
        sharedData.edit().putInt("score", highScoreData.getScore()).apply();
        sharedData.edit().commit();
    }

    public HighScoreData getStoredHighscore(){
        HighScoreData storedData = new HighScoreData();
        storedData.setUsername(sharedData.getString("username", "user"));
        storedData.setScore(sharedData.getInt("score", 0));
        return  storedData;
    }

    public void removeHighscoreData(){
        sharedData.edit()
                .remove("username")
                .remove("score")
                .apply();
    }
}
