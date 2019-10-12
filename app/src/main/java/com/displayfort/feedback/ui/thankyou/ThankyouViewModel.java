
package com.displayfort.feedback.ui.thankyou;

import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.ui.base.BaseViewModel;
import com.displayfort.feedback.utils.rx.SchedulerProvider;


public class ThankyouViewModel extends BaseViewModel<ThankyouNavigator> {

    public ThankyouViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onGoBack() {
        getNavigator().onGoBack();
    }


}

