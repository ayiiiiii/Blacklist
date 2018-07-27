package com.testbirds.blacklist.ui.MainActivity;

import com.testbirds.blacklist.modals.App;
import com.testbirds.blacklist.ui.base.IView;

import java.util.List;

public interface IMainActivityView extends IView
{
    void appsReady(List<App> appList);
}