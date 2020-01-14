
package com.displayfort.feedback.data.remote;

import com.displayfort.feedback.data.model.api.request.FeedBackRequest;
import com.displayfort.feedback.data.model.api.request.LoginRequest;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.data.model.api.response.LangugeResponse;
import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.data.model.api.response.LogoutResponse;
import com.displayfort.feedback.utils.AppConstants;
import com.rx2androidnetworking.Rx2ANRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Yogesh  on 07/07/17.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }


    @Override
    public Single<LogoutResponse> doLogoutApiCall() {
        //TODO
        return Rx2AndroidNetworking.post(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LogoutResponse.class);
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<FeedBackResponse> doServergetFeedbackQuestion(String language) {
        return Rx2AndroidNetworking.get(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_FEEDBACK_QUESTION + language)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(FeedBackResponse.class);
    }

    @Override
    public Single<LangugeResponse> doServergetLangugeList() {
        return Rx2AndroidNetworking.get(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_GET_LANG_LIST)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectSingle(LangugeResponse.class);
    }

    @Override
    public Single<FeedBackResponse> doServergetSubmitFeedback(FeedBackRequest.feedbackReq request) {
        return Rx2AndroidNetworking.post(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_SUBMIT_FEEDBACK)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(FeedBackResponse.class);
    }

    @Override
    public Single<LoginResponse> doLicenseCheckApiCall(LoginRequest.LicenseRequest request) {
        return Rx2AndroidNetworking.post(ApiHeader.CUSTOM_BASE_URL + ApiEndPoint.ENDPOINT_SERVER_LICENSE)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

}
