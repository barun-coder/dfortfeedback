
package com.displayfort.feedback.ui.feedback;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;


import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class FeedbackActivityModule {

    @Provides
    FeedBackViewModel addProfessionalExpViewModel(DataManager dataManager,
                                                  SchedulerProvider schedulerProvider) {
        return new FeedBackViewModel(dataManager, schedulerProvider);
    }


    @Provides
    OptionsAdapter provideResponsibilityAdapter() {
        return new OptionsAdapter(new ArrayList<>());
    }


    @Provides
    GridLayoutManager provideGridLayoutManager(FeedBackActivity activity) {
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 4);
        return layoutManager;
    }

}
