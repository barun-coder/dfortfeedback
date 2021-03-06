
package com.displayfort.feedback.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.displayfort.feedback.BR;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.databinding.ActivitySplashBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.base.BaseAnimation;
import com.displayfort.feedback.ui.feedback.FeedBackActivity;
import com.displayfort.feedback.ui.login.LoginActivity;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivitySplashBinding activitySplashBinding;
    private SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = FeedBackActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }

    @Override
    public void startAnimation() {
        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        animZoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSplashViewModel.decideNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplashBinding.logo.startAnimation(animZoomIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        activitySplashBinding = getViewDataBinding();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashViewModel.startAnimation();
            }
        }, 1500);


    }

}
