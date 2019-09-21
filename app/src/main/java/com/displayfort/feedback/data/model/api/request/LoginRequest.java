
package com.displayfort.feedback.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Husain  on 07/07/17.
 */

public final class LoginRequest {

    private LoginRequest() {
        // This class is not publicly instantiable
    }

    public static class ServerLoginRequest {

        @Expose
        @SerializedName("user")
        private String user;

        @Expose
        @SerializedName("password")
        private String password;


        public ServerLoginRequest(String user, String password) {
            this.user = user;
            this.password = password;
        }


    }
}
