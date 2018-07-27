package com.testbirds.blacklist.data;

import com.testbirds.blacklist.data.applications.ApplicationsHelper;
import com.testbirds.blacklist.data.db.DBHelper;
import com.testbirds.blacklist.modals.App;

import java.util.List;

import javax.inject.Inject;

public class AppDataManager implements DataManager {
    ApplicationsHelper applicationsHelper;
    DBHelper dbHelper;

    @Inject
    public AppDataManager(ApplicationsHelper applicationsHelper, DBHelper dbHelper) {
        this.applicationsHelper = applicationsHelper;
        this.dbHelper = dbHelper;
    }

    @Override
    public List<App> getApps() {
        return applicationsHelper.getApps();
    }

    @Override
    public List<App> getBlackListedApps() {
        return dbHelper.getBlackListedApps();
    }

    @Override
    public void addBlackListedApp(App app) {
        dbHelper.addBlackListedApp(app);
    }
}
