
package com.displayfort.feedback.data;

import android.content.Context;

import com.displayfort.feedback.data.local.db.DbHelper;
import com.displayfort.feedback.data.local.prefs.AppPreferencesHelper;
import com.displayfort.feedback.data.local.prefs.PreferencesHelper;
import com.displayfort.feedback.data.model.api.request.FeedBackRequest;
import com.displayfort.feedback.data.model.api.request.LoginRequest;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.data.model.api.response.LangugeResponse;
import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.data.model.api.response.LogoutResponse;
import com.displayfort.feedback.data.model.db.Option;
import com.displayfort.feedback.data.model.db.Question;
import com.displayfort.feedback.data.model.db.User;
import com.displayfort.feedback.data.model.others.QuestionCardData;
import com.displayfort.feedback.data.remote.ApiHeader;
import com.displayfort.feedback.data.remote.ApiHelper;
import com.displayfort.feedback.utils.AppConstants;
import com.displayfort.feedback.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Yogesh  on 07/07/17.
 */
@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        return mApiHelper.doLogoutApiCall();
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Single<FeedBackResponse> doServergetFeedbackQuestion(String language) {
        return mApiHelper.doServergetFeedbackQuestion(language);
    }

    @Override
    public Single<LangugeResponse> doServergetLangugeList() {
        return mApiHelper.doServergetLangugeList();
    }

    @Override
    public Single<FeedBackResponse> doServergetSubmitFeedback(FeedBackRequest.feedbackReq request) {
        return mApiHelper.doServergetSubmitFeedback(request);
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return mDbHelper.getAllQuestions();
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }


    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }


    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentBgPath(String BgPath) {
        mPreferencesHelper.setCurrentBgPath(BgPath);
    }

    @Override
    public void setValue(String key, String userName) {
        mPreferencesHelper.setValue(key, userName);
    }

    @Override
    public String getValue(String key) {
        return mPreferencesHelper.getValue(key);
    }

    @Override
    public String getCurrentBgPath() {
        return mPreferencesHelper.getCurrentBgPath();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String profilePicUrl) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(profilePicUrl);
    }


    @Override
    public Observable<List<Option>> getOptionsForQuestionId(Long questionId) {
        return mDbHelper.getOptionsForQuestionId(questionId);
    }

    @Override
    public Single<LoginResponse> doLicenseCheckApiCall(LoginRequest.LicenseRequest request) {
        return mApiHelper.doLicenseCheckApiCall(request);
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<Boolean> isOptionEmpty() {
        return mDbHelper.isOptionEmpty();
    }

    @Override
    public Observable<Boolean> isQuestionEmpty() {
        return mDbHelper.isQuestionEmpty();
    }

    @Override
    public Observable<Boolean> saveOption(Option option) {
        return mDbHelper.saveOption(option);
    }

    @Override
    public Observable<Boolean> saveOptionList(List<Option> optionList) {
        return mDbHelper.saveOptionList(optionList);
    }

    @Override
    public Observable<Boolean> saveQuestion(Question question) {
        return mDbHelper.saveQuestion(question);
    }

    @Override
    public Observable<Boolean> saveQuestionList(List<Question> questionList) {
        return mDbHelper.saveQuestionList(questionList);
    }


    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateApiHeader(String userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath) {

        setAccessToken(accessToken);
        setCurrentUserId(userId + "");
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);
        setCurrentUserProfilePicUrl(profilePicPath);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateUserInfo(String accessToken, String userId, LoggedInMode loggedInMode, String userName, String email) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentUserEmail(email);


        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateLoginUserInfo(String accessToken, String userId, LoggedInMode loggedInMode, String userName, String bgPath, String profilePicPath) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentBgPath(bgPath);
        setCurrentUserProfilePicUrl(profilePicPath);
        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateLoginUserInfo(String accessToken, String userId, LoggedInMode loggedInMode, String userName,
                                    String bgPath, String profilePicPath, String header_text, String sub_header_text) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        setCurrentUserName(userName);
        setCurrentBgPath(bgPath);
        setCurrentUserProfilePicUrl(profilePicPath);
        updateApiHeader(userId, accessToken);
        setValue(AppPreferencesHelper.PREF_KEY_HEADER_TEXT, header_text);
        setValue(AppPreferencesHelper.PREF_KEY_SUB_HEADER_TEXT, sub_header_text);
    }


    @Override
    public void updateUserInfo(String accessToken, String userId, LoggedInMode loggedInMode) {
        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
        updateApiHeader(userId, accessToken);
    }

    @Override
    public void updateUserInfo(String userId) {
        setCurrentUserId(userId);
    }

}
