
package com.displayfort.feedback.ui.thankyou;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.displayfort.feedback.BR;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.databinding.ActivityThankyouBinding;
import com.displayfort.feedback.databinding.ActivityThankyouBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.base.BaseAnimation;
import com.displayfort.feedback.ui.feedback.FeedBackActivity;
import com.displayfort.feedback.ui.login.LoginActivity;

import javax.inject.Inject;


public class ThankyouActivity extends BaseActivity<ActivityThankyouBinding, ThankyouViewModel> implements ThankyouNavigator {

    @Inject
    ViewModelProviderFactory factory;
    ActivityThankyouBinding ActivityThankyouBinding;
    private ThankyouViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_thankyou;
    }

    @Override
    public ThankyouViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this, factory).get(ThankyouViewModel.class);
        return mSplashViewModel;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ThankyouActivity.class);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(ThankyouActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = FeedBackActivity.newIntent(ThankyouActivity.this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        ActivityThankyouBinding = getViewDataBinding();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        }, 6000);


    }

}
