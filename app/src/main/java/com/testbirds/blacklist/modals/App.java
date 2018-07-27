package com.testbirds.blacklist.modals;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class App extends SugarRecord {

    @Unique
    private String appId;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
