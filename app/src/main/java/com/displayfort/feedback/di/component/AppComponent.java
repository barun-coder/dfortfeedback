
package com.displayfort.feedback.di.component;

import android.app.Application;

import com.displayfort.feedback.DfFeedBack;
import com.displayfort.feedback.di.builder.ActivityBuilder;
import com.displayfort.feedback.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Yogesh  on 07/07/17.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(DfFeedBack app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
