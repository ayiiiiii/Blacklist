package com.testbirds.blacklist;

import com.orm.SugarApp;
import com.testbirds.blacklist.di.DaggerComponent;
import com.testbirds.blacklist.di.DaggerDaggerComponent;
import com.testbirds.blacklist.di.DaggerModule;

import dagger.Module;

@Module
public class AppController extends SugarApp {
    private DaggerComponent daggerComponent;
    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        daggerComponent = createDaggerComponent();

        mInstance = this;
    }

    public DaggerComponent getDaggerComponent() {
        return daggerComponent == null ? createDaggerComponent() : daggerComponent;
    }


    private DaggerComponent createDaggerComponent() {
        return DaggerDaggerComponent.builder().daggerModule(new DaggerModule(this)).build();
    }

    public void clearComponent() {
        daggerComponent = null;
    }

    public static AppController getInstance() {
        return mInstance;
    }
}
