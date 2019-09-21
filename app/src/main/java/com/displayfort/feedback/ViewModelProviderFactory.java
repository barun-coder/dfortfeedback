package com.displayfort.feedback;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.ui.feedback.FeedBackViewModel;
import com.displayfort.feedback.ui.login.LoginViewModel;
import com.displayfort.feedback.ui.splash.SplashViewModel;
import com.displayfort.feedback.ui.thankyou.ThankyouViewModel;
import com.displayfort.feedback.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FeedBackViewModel.class)) {
            //noinspection unchecked
            return (T) new FeedBackViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ThankyouViewModel.class)) {
            //noinspection unchecked
            return (T) new ThankyouViewModel(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
