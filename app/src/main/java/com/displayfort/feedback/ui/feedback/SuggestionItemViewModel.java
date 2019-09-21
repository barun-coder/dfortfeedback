
package com.displayfort.feedback.ui.feedback;

import android.databinding.ObservableField;

import com.displayfort.feedback.data.model.api.response.FeedBackResponse;


public class SuggestionItemViewModel {

    public final ObservableField<String> id;
    public final ObservableField<String> name;
    private final FeedBackResponse.SubDao genericDao;

    private SuggestionItemViewModelListener mResponseListener;

    public SuggestionItemViewModel(FeedBackResponse.SubDao generic, SuggestionItemViewModelListener listener) {
        this.mResponseListener = listener;
        this.genericDao = generic;
        name = new ObservableField<>(generic.getValue());
        id = new ObservableField<>(generic.getId());
    }

    public void onObjAdd() {
        mResponseListener.onObjAdd(genericDao);
    }

    public interface SuggestionItemViewModelListener {

        void onObjAdd(FeedBackResponse.SubDao generic);
    }
}
