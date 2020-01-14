
package com.displayfort.feedback.ui.feedback;


import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.data.model.api.response.LangugeResponse;
import com.displayfort.feedback.ui.base.BaseNavigator;

public interface FeedBackNavigator extends BaseNavigator {


    void showFeedback(FeedBackResponse response);

    void onFeedbackClick(int question);

    void openLoginActivity();

    void onSubmit();

    void onSuccessSubmit(FeedBackResponse response);

    void onLeaveComment();

    void onCancel();

    void onChangeLang();

    void onLogoutClick();

    void showLanguageList(LangugeResponse response);
}
