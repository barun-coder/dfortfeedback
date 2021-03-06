
package com.displayfort.feedback.ui.login;

import android.text.TextUtils;

import com.androidnetworking.error.ANError;
import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.data.model.api.ApiError;
import com.displayfort.feedback.data.model.api.NetworkConstant;
import com.displayfort.feedback.data.model.api.request.LoginRequest;
import com.displayfort.feedback.ui.base.BaseViewModel;
import com.displayfort.feedback.utils.rx.SchedulerProvider;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public int isEmailAndPasswordValid(String email, String password) {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return 1;
        }
        if (TextUtils.isEmpty(password)) {
            return 2;
        }
        return 0;
    }

    public void login(String email, String password) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .doOnSuccess(response -> getDataManager()
                        .updateLoginUserInfo(
                                response.getData().getToken_code(),
                                response.getData().getUser_id(),
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getData().getCompany_name(),
                                response.getData().getBg_image(),
                                response.getData().getLogo()
                        ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity(response.getData());
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);

                }));
    }


    public void onServerLoginClick() {
        getNavigator().login();
    }

    public void onForgotPasswordClick() {
        getNavigator().OpenForgotPasswordActivity();
    }
}
