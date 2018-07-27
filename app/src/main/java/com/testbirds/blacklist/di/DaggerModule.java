package com.testbirds.blacklist.di;

import android.app.Application;
import android.content.Context;

import com.testbirds.blacklist.data.AppDataManager;
import com.testbirds.blacklist.data.DataManager;
import com.testbirds.blacklist.data.applications.AppApplicationsHelper;
import com.testbirds.blacklist.data.applications.ApplicationsHelper;
import com.testbirds.blacklist.data.db.AppDBHelper;
import com.testbirds.blacklist.data.db.DBHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaggerModule {
    private Context context;

    public DaggerModule(Application app) {
        this.context = app;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(ApplicationsHelper applicationsHelper, DBHelper dbHelper) {
        return new AppDataManager(applicationsHelper, dbHelper);
    }

    @Provides
    @Singleton
    ApplicationsHelper provideApplicationsHelper(Context context) {
        return new AppApplicationsHelper(context);
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper() {
        return new AppDBHelper();
    }
}
