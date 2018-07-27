package com.testbirds.blacklist.data.db;

import com.testbirds.blacklist.modals.App;

import java.util.List;

public interface DBHelper {
    List<App> getBlackListedApps();
    void addBlackListedApp(App app);
}
