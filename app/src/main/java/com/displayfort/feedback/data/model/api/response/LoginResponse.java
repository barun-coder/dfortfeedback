
package com.displayfort.feedback.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Husain  on 07/07/17.
 */

public final class LoginResponse {

    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private UserDao data;

    @Expose
    @SerializedName("message")
    private String message;


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        LoginResponse that = (LoginResponse) object;


        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public UserDao getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }


    public static class UserDao {
        @Expose
        @SerializedName("logo")
        private String logo;
        @Expose
        @SerializedName("bg_image")
        private String bg_image;

        @Expose
        @SerializedName("company_name")
        private String company_name;
        @Expose
        @SerializedName("token_code")
        private String token_code;

        @Expose
        @SerializedName("user_id")
        private String user_id;


        public String getToken_code() {
            return token_code;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getLogo() {
            return logo;
        }

        public String getBg_image() {
            return bg_image;
        }

        public String getCompany_name() {
            return company_name;
        }
    }
}
