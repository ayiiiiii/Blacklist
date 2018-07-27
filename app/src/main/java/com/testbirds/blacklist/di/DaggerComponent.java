package com.testbirds.blacklist.di;

import com.testbirds.blacklist.ui.MainActivity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules =  {DaggerModule.class})
public interface DaggerComponent {
    void inject(MainActivity mainActivity);
}