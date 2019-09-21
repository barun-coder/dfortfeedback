
package com.displayfort.feedback.ui.splash;

import android.os.Handler;

import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.ui.base.BaseViewModel;
import com.displayfort.feedback.utils.rx.SchedulerProvider;


public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }



    public void callActivityAfterSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                decideNextActivity();
            }
        }, 2000);
    }

    protected void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openMainActivity();
        }
    }

    protected void startAnimation() {
        getNavigator().startAnimation();
    }
}

