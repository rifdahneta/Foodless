package pnj.ac.id.foodless.Activity;

import android.app.Application;
import android.content.SharedPreferences;

public class MainApp extends Application {
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static SharedPreferences sharedPreferences;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
    }


}
