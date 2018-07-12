package upc.edu.pe.sentinel;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class SentinelApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
