
package com.displayfort.feedback.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husain  on 07/07/17.
 */

public final class FeedBackRequest {

    private FeedBackRequest() {
        // This class is not publicly instantiable
    }

    public static class feedbackReq {

        @Expose
        @SerializedName("rating")
        private String rating;

        @Expose
        @SerializedName("user_feedback")
        private List<String> user_feedback;
        @Expose
        @SerializedName("user_comment")
        private String user_comment;
        @Expose
        @SerializedName("user_mobileno")
        private String user_mobileno;

        @Expose
        @SerializedName("user_emailid")
        private String user_emailid;

        public feedbackReq(String rating, List<String> user_feedback, String user_comment, String user_mobileno, String user_emailid) {
            this.rating = rating;
            this.user_feedback = user_feedback;
            this.user_comment = user_comment;
            this.user_mobileno = user_mobileno;
            this.user_emailid = user_emailid;
        }

        public String getRating() {
            return rating;
        }

        public List<String> getUser_feedback() {
            return user_feedback;
        }

        public String getUser_comment() {
            return user_comment;
        }

        public String getUser_mobileno() {
            return user_mobileno;
        }

        public String getUser_emailid() {
            return user_emailid;
        }
    }
}
