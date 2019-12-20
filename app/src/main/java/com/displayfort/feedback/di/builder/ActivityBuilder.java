
package com.displayfort.feedback.di.builder;


import com.displayfort.feedback.ui.feedback.FeedBackActivity;
import com.displayfort.feedback.ui.feedback.FeedbackActivityModule;
import com.displayfort.feedback.ui.login.LicenseLoginActivity;
import com.displayfort.feedback.ui.login.LoginActivity;
import com.displayfort.feedback.ui.splash.SplashActivity;
import com.displayfort.feedback.ui.thankyou.ThankyouActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Husain  on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract LicenseLoginActivity bindLicenseLoginActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract ThankyouActivity bindThankyouActivity();

    @ContributesAndroidInjector(modules = {FeedbackActivityModule.class})
    abstract FeedBackActivity bindFeedBackActivity();


}
