
package com.displayfort.feedback.ui.feedback;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.databinding.ItemFeedbackBinding;
import com.displayfort.feedback.ui.base.BaseViewHolder;

import java.util.List;


public class OptionsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    public static final int VIEW_TYPE_SUGGEST = 2;

    private List<FeedBackResponse.SubDao> optionList;

    private AdapterListener mListener;

    private ResponseAdapterListener responseAdapterListener;

    private int type;

    public OptionsAdapter(List<FeedBackResponse.SubDao> list) {
        this.optionList = list;
    }

    @Override
    public int getItemCount() {
        if (optionList != null && optionList.size() > 0) {
            return optionList.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (optionList != null && !optionList.isEmpty()) {
            return VIEW_TYPE_SUGGEST;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFeedbackBinding itemSugesstionBinding = ItemFeedbackBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new SuggestionViewHolder(itemSugesstionBinding);

    }

    public void addItems(List<FeedBackResponse.SubDao> list, int type) {
        optionList = (list);
        this.type = type;
        notifyDataSetChanged();
    }

    public List<FeedBackResponse.SubDao> getItems() {
        return optionList;
    }

    public void clearItems() {
        optionList.clear();
    }

    public void setListener(AdapterListener listener) {
        this.mListener = listener;
    }

    public void setResponseListener(ResponseAdapterListener listener) {
        this.responseAdapterListener = listener;
    }

    public interface AdapterListener {

        void onRetryClick();
    }

    public interface ResponseAdapterListener {

        void onRemoveObj(FeedBackResponse.SubDao generic);

        void onAddObj(FeedBackResponse.SubDao generic);
    }


    public class SuggestionViewHolder extends BaseViewHolder implements SuggestionItemViewModel.SuggestionItemViewModelListener {
        private FeedBackResponse.SubDao generic;
        private ItemFeedbackBinding mBinding;
        private int pos;
        private SuggestionItemViewModel suggestionItemViewModel;

        public SuggestionViewHolder(ItemFeedbackBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            this.pos = position;
            final FeedBackResponse.SubDao generic = optionList.get(position);
            this.generic = optionList.get(position);
            suggestionItemViewModel = new SuggestionItemViewModel(generic, this);
            mBinding.setViewModel(suggestionItemViewModel);
            mBinding.executePendingBindings();
            mBinding.cbdepartment.setSelected(generic.isSelected());
        }


        @Override
        public void onObjAdd(FeedBackResponse.SubDao generic) {
            FeedBackResponse.SubDao subDao = optionList.get(pos);
            if (subDao.isSelected()) {
                subDao.setSelected(false);
            } else {
                subDao.setSelected(true);
            }
            mBinding.cbdepartment.setSelected(subDao.isSelected());
            responseAdapterListener.onAddObj(generic);

        }
    }


}