
package com.displayfort.feedback.ui.feedback;

import android.content.Context;

import com.displayfort.feedback.DfFeedBack;
import com.displayfort.feedback.data.DataManager;
import com.displayfort.feedback.data.model.api.request.FeedBackRequest;
import com.displayfort.feedback.data.model.api.request.LoginRequest;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.ui.base.BaseViewModel;
import com.displayfort.feedback.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.List;

import RetroFit.BaseRequest;
import RetroFit.RequestReceiver;


public class FeedBackViewModel extends BaseViewModel<FeedBackNavigator> {

    public FeedBackViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
//        getLangugaeList();
    }

    //multilingual/multilingual/getLanguage
    public void getLangugaeList() {
//        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServergetLangugeList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().showLanguageList(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void getFeedbackQuestion(String language) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServergetFeedbackQuestion(language)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().showFeedback(response);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void getSubmitFeedback(String rating, List<String> user_feedback, String user_comment, String user_mobileno, String user_emailid) {
        setIsLoading(true);
        FeedBackRequest.feedbackReq request = new FeedBackRequest.feedbackReq(rating, user_feedback, user_comment, user_mobileno, user_emailid);
        loginRequest(DfFeedBack.getContext(), request);
    }

    private void loginRequest(Context context, FeedBackRequest.feedbackReq request) {
        BaseRequest baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReceiver() {

            @Override
            public void onSuccess(int requestCode, String fullResponse, Object dataObject, int StatusCode) {
                if (StatusCode == 200) {
                    getNavigator().onSuccessSubmit(new FeedBackResponse());
                } else {

                }
                setIsLoading(false);
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                setIsLoading(false);
            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {
                setIsLoading(false);
            }
        });
        request.setUser_id(getDataManager().getCurrentUserId());
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JsonElement jsonObject = gson.toJsonTree(request);

        baseRequest.callAPIPost(1, jsonObject.getAsJsonObject(), "feedback/feedback",
                getDataManager().getAccessToken(), getDataManager().getCurrentUserId());
    }


    public void onFeedbackClick(int question) {
        getNavigator().onFeedbackClick(question);
    }

    public void onLogout() {
        getNavigator().onLogoutClick();

    }

    public void getLogout() {
        getDataManager().setUserAsLoggedOut();
        setIsLoading(false);
        getNavigator().openLoginActivity();
    }

    public void onSubmit() {
        getNavigator().onSubmit();
    }

    public void onChangeLang() {
        getNavigator().onChangeLang();
    }

    public void onLeaveComment() {
        getNavigator().onLeaveComment();
    }

    public void onCancel() {
        getNavigator().onCancel();
    }
}
