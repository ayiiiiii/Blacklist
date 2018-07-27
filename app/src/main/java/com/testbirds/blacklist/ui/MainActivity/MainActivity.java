package com.testbirds.blacklist.ui.MainActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;

import com.testbirds.blacklist.AppController;
import com.testbirds.blacklist.R;
import com.testbirds.blacklist.data.DataManager;
import com.testbirds.blacklist.modals.App;
import com.testbirds.blacklist.ui.base.BaseActivity;
import com.testbirds.blacklist.utils.Const;

import java.util.List;

import javax.inject.Inject;

import static com.testbirds.blacklist.utils.Utils.isAccessibilitySettingsOn;

public class MainActivity extends BaseActivity implements IMainActivityView {

    @Inject
    DataManager dataManager;
    private RecyclerView rvApps, rvBlacklistedApps;
    private Switch swNoDistractionMode;
    private AppAdapter appAdapter;
    private BlackListAppAdapter blackListedAppAdapter;
    private MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AppController) getApplication()).getDaggerComponent().inject(this);

        rvApps = findViewById(R.id.rvApps);
        rvBlacklistedApps = findViewById(R.id.rvBlacklistedApps);
        swNoDistractionMode = findViewById(R.id.swNoDistractionMode);

        appAdapter = new AppAdapter();
        blackListedAppAdapter = new BlackListAppAdapter(dataManager);

        rvApps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvBlacklistedApps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        rvApps.setAdapter(appAdapter);
        rvBlacklistedApps.setAdapter(blackListedAppAdapter);

        mainActivityPresenter = new MainActivityPresenter(this, dataManager);
        mainActivityPresenter.getApplications();

        appAdapter.setOnAppClickedListener(new AppAdapter.OnAppClickedListener() {
            @Override
            public void onAppClicked(App app) {
                dataManager.addBlackListedApp(app);
                blackListedAppAdapter.updateList();
            }
        });

        if (!isAccessibilitySettingsOn(getApplicationContext())) {
            swNoDistractionMode.setChecked(false);
        }
        else
        {
            swNoDistractionMode.setChecked(true);
        }

        swNoDistractionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAccessibilitySettings();
            }
        });
    }

    @Override
    public void appsReady(List<App> appList) {
        appAdapter.updateList(appList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Const.START_ACTIVITY_FOR_RESULT_ACCESSIBILITY_SETTINGS)
        {
            if (isAccessibilitySettingsOn(getApplicationContext()))
            {
                swNoDistractionMode.setChecked(true);
            }
            else
            {
                swNoDistractionMode.setChecked(false);
            }
        }
    }

    private void startAccessibilitySettings()
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getText(R.string.enable_or_disable_accessibility_service_to_enable_or_disable_no_distraction_mode));
        builder.setCancelable(false);

        builder.setPositiveButton(
                getText(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
                        startActivityForResult(intent, Const.START_ACTIVITY_FOR_RESULT_ACCESSIBILITY_SETTINGS);
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();

    }


}
