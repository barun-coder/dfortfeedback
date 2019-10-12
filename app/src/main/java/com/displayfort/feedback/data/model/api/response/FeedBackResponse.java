
package com.displayfort.feedback.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husain  on 07/07/17.
 */

public final class FeedBackResponse {

    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private List<FeedbackDao> data;

    @Expose
    @SerializedName("total")
    private int total;


    public static class FeedbackDao {

        @Expose
        @SerializedName("feed_back_question_count")
        private String feed_back_question_count;

        @Expose
        @SerializedName("feed_back_type")
        private String feed_back_type;
        @Expose
        @SerializedName("feed_back_type_id")
        private String feed_back_ID;
        @Expose
        @SerializedName("feedback_type_header")
        private String feedback_type_header;

        @Expose
        @SerializedName("feed_back_path")
        private String feed_back_path;

        @Expose
        @SerializedName("feed_back_question")
        private List<SubDao> feed_back_question;

        public String getFeed_back_question_count() {
            return feed_back_question_count;
        }

        public String getFeed_back_type() {
            return feed_back_type;
        }

        public String getFeed_back_ID() {
            return feed_back_ID;
        }

        public String getFeed_back_path() {
            return feed_back_path;
        }

        public String getFeedback_type_header() {
            return feedback_type_header;
        }

        public List<SubDao> getFeed_back_question() {
            return feed_back_question;
        }
    }


    public static class SubDao {

        @Expose
        @SerializedName("value")
        private String value;

        @Expose
        @SerializedName("id")
        private String id;

        private boolean isSelected = false;

        public String getValue() {
            return value;
        }

        public String getId() {
            return id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public List<FeedbackDao> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }
}
