package com.testbirds.blacklist.data.applications;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.testbirds.blacklist.modals.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppApplicationsHelper implements ApplicationsHelper {

    Context context;
    PackageManager pm;

    @Inject
    public AppApplicationsHelper(Context context) {
        this.context = context;
        pm = context.getPackageManager();
    }

    @Override
    public List<App> getApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities( mainIntent, 0);

        List<App> appList = new ArrayList<>();

        for (int i=0; i<pkgAppsList.size(); i++)
        {
            ResolveInfo info = pkgAppsList.get(i);
            App app = new App();
            app.setAppId(info.activityInfo.packageName);
            app.setTitle(info.loadLabel(pm).toString());

            appList.add(app);
        }
        return appList;
    }
}
