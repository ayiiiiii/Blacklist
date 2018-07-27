package com.testbirds.blacklist.ui.MainActivity;

import com.testbirds.blacklist.ui.base.IPresenter;

public interface IMainActivityPresenter<V extends IMainActivityView> extends IPresenter<V> {
    void getApplications();
}