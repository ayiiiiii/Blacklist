package com.testbirds.blacklist.ui.MainActivity;

import android.app.Activity;

import com.testbirds.blacklist.data.DataManager;
import com.testbirds.blacklist.modals.App;
import com.testbirds.blacklist.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter<V extends IMainActivityView> extends BasePresenter<V> implements IMainActivityPresenter<V> {
    private DataManager dataManager;

    public MainActivityPresenter(Activity activity, DataManager dataManager) {
        super(activity);
        this.dataManager = dataManager;
    }

    @Override
    public void getApplications() {
        getMvpView().showLoading();

        Observable.just(dataManager.getApps())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<App>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<App> appList) {
                        getMvpView().appsReady(appList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().dissmisLoading();
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().dissmisLoading();
                    }
                });


    }
}
