package com.example.lehieudut.samplemvp;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.lehieudut.samplemvp.service.core.ApiClient;
import com.example.lehieudut.samplemvp.service.core.ApiConfig;

import org.androidannotations.annotations.EApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by lehieudut on 2/27/18.
 */
@EApplication
public class App extends MultiDexApplication {
    private static final String TAG = App.class.getSimpleName();
    private static App sInstance = null;

    /**
     * Get instance of app
     *
     * @return app
     */
    public static synchronized App getInstance() {
        if (sInstance == null) {
            sInstance = new App();
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());

        // realm
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .modules(Realm.getDefaultModule())
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();

        /**
         * start service
         */
        ApiConfig apiConfig = ApiConfig.builder()
                .baseUrl("http://www.splashbase.co/api/v1/")
                .context(getApplicationContext())
                .auth("")
                .build();
        ApiClient.getInstance().init(apiConfig);
    }
}
