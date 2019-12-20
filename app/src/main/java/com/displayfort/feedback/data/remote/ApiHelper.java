
package com.displayfort.feedback.data.remote;

import com.displayfort.feedback.data.model.api.request.FeedBackRequest;
import com.displayfort.feedback.data.model.api.request.LoginRequest;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.data.model.api.response.LangugeResponse;
import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.data.model.api.response.LogoutResponse;

import java.io.File;

import io.reactivex.Single;

/**
 * Created by Yogesh  on 07/07/17.
 */

public interface ApiHelper {

    Single<LogoutResponse> doLogoutApiCall();

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<FeedBackResponse> doServergetFeedbackQuestion(String language);

    Single<LangugeResponse> doServergetLangugeList();

    Single<FeedBackResponse> doServergetSubmitFeedback(FeedBackRequest.feedbackReq request);

    Single<LoginResponse> doLicenseCheckApiCall(LoginRequest.LicenseRequest request);

    ApiHeader getApiHeader();


}
