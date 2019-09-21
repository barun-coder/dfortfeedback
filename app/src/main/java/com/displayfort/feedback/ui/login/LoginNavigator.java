
package com.displayfort.feedback.ui.login;


import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.ui.base.BaseNavigator;

public interface LoginNavigator  extends BaseNavigator {

    void login();

    void OpenForgotPasswordActivity();

    void openMainActivity(LoginResponse.UserDao data);

    void OpenVirificationActivity(String message);
}
