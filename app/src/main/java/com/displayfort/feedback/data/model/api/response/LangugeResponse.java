
package com.displayfort.feedback.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Husain  on 07/07/17.
 */

public final class LangugeResponse {

    @Expose
    @SerializedName("status")
    private boolean status;
    @Expose
    @SerializedName("data")
    private List<LangugeDao> data;

    @Expose
    @SerializedName("total")
    private int total;


    public static class LangugeDao {

        @Expose
        @SerializedName("lang_code")
        private String lang_code;

        @Expose
        @SerializedName("lang_name")
        private String lang_name;

        public String getLang_code() {
            return lang_code;
        }

        public String getLang_name() {
            return lang_name;
        }

        public LangugeDao(String lang_code, String lang_name) {
            this.lang_code = lang_code;
            this.lang_name = lang_name;
        }
    }


    public boolean isStatus() {
        return status;
    }

    public List<LangugeDao> getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }
}
