package com.ostmodern.videodemo;

import com.ostmodern.videodemo.database.Datasource;

/**
 * Created by philip.arnold on 14/03/2016.
 */
public class Application extends android.app.Application {
    public static Datasource datasource;
    public String PACKAGE_NAME;
    @Override
    public void onCreate() {
        super.onCreate();
        PACKAGE_NAME = getApplicationContext().getPackageName();
        datasource = new Datasource(this);
        datasource.open();
    }
}
