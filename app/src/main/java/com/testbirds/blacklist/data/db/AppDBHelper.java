package com.testbirds.blacklist.data.db;

import com.testbirds.blacklist.modals.App;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDBHelper implements DBHelper {

    @Inject
    public AppDBHelper() {
    }

    @Override
    public List<App> getBlackListedApps() {
        return App.listAll(App.class);
    }

    @Override
    public void addBlackListedApp(App app) {
        app.save();
    }
}
