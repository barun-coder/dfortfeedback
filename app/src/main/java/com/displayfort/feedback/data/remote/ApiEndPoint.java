
package com.displayfort.feedback.data.remote;

import com.displayfort.feedback.BuildConfig;

/**
 * Created by Yogesh  on 07/07/17.
 */

public final class ApiEndPoint {
    public static final String ENDPOINT_SERVER_LICENSE = BuildConfig.BASE_URL + "login/licence";
    static final String ENDPOINT_SERVER_LOGIN = BuildConfig.BASE_URL + "login/ThirdParty";
    static final String ENDPOINT_SERVER_FEEDBACK_QUESTION = BuildConfig.BASE_URL + "feedback/feedbackQuestion/lang:";
    static final String ENDPOINT_SERVER_SUBMIT_FEEDBACK = BuildConfig.BASE_URL + "feedback/feedback";
    static final String ENDPOINT_SERVER_GET_LANG_LIST = BuildConfig.BASE_URL + "multilingual/multilingual/getLanguage";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}



