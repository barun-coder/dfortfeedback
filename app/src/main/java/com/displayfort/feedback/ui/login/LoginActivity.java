
package com.displayfort.feedback.ui.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.displayfort.feedback.BR;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.data.model.api.ApiError;
import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.databinding.ActivityLoginBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.base.BaseAnimation;
import com.displayfort.feedback.ui.feedback.FeedBackActivity;
import com.displayfort.feedback.utils.DialogUtils;

import javax.inject.Inject;


public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        ApiError apiError = new ApiError(((ANError) throwable).getErrorBody(), ((ANError) throwable).getErrorCode());
        DialogUtils.showAlertDialog(this, apiError.getMessage());
    }

    @Override
    public void login() {
        String email = mActivityLoginBinding.username.getText().toString();
        String password = mActivityLoginBinding.password.getText().toString();
        int validation = mLoginViewModel.isEmailAndPasswordValid(email, password);
        if (validation == 0) {
            hideKeyboard();
            mLoginViewModel.login(email, password);
        } else {
            setError(validation);
        }
    }

    private void setError(int validation) {
        switch (validation) {
            case 1:
                mActivityLoginBinding.username.setError("Please Enter Username");
                break;
            case 2:
                mActivityLoginBinding.password.setError("Please Enter Password");
                break;

        }
    }

    @Override
    public void OpenForgotPasswordActivity() {


    }

    @Override
    public void openMainActivity(LoginResponse.UserDao data) {
        Intent intent = FeedBackActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
    }


    @Override
    public void OpenVirificationActivity(String message) {
        DialogUtils.showAlertDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
        mActivityLoginBinding.password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }
}
