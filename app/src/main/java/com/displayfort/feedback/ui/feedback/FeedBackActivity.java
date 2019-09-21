
package com.displayfort.feedback.ui.feedback;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.error.ANError;
import com.displayfort.feedback.BR;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.data.model.api.ApiError;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.databinding.ActivityFreedbackBinding;
import com.displayfort.feedback.databinding.ItemFeedbackBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.login.LoginActivity;
import com.displayfort.feedback.ui.thankyou.ThankyouActivity;
import com.displayfort.feedback.utils.AppConstants;
import com.displayfort.feedback.utils.BindingUtils;
import com.displayfort.feedback.utils.DialogUtils;
import com.displayfort.feedback.utils.Utility;
import com.displayfort.feedback.utils.ValidationUtils;
import com.displayfort.feedback.widgets.EditextRegular;
import com.displayfort.feedback.widgets.TextviewRegular;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;


public class FeedBackActivity extends BaseActivity<ActivityFreedbackBinding, FeedBackViewModel> implements FeedBackNavigator, OptionsAdapter.AdapterListener, OptionsAdapter.ResponseAdapterListener {
    @Inject
    OptionsAdapter suggestionAdapter;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    ViewModelProviderFactory factory;
    private FeedBackViewModel feedBackViewModel;
    private ActivityFreedbackBinding mActivityFreedbackBinding;
    private Set<String> feedback = new HashSet<>();
    private String currentSelection = "";
    private String comment = "", mobile = "", emailId = "";

    public static Intent newIntent(Context context) {
        return new Intent(context, FeedBackActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_freedback;
    }

    @Override
    public FeedBackViewModel getViewModel() {
        feedBackViewModel = ViewModelProviders.of(this, factory).get(FeedBackViewModel.class);
        return feedBackViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        super.handleError(throwable);
        ApiError apiError = new ApiError(((ANError) throwable).getErrorBody(), ((ANError) throwable).getErrorCode());
        DialogUtils.showAlertDialog(this, apiError.getMessage());
    }

    @Override
    public void showFeedback(FeedBackResponse response) {
        int totalcount = response.getTotal();
        for (int i = 0; i < totalcount; i++) {
            FeedBackResponse.FeedbackDao feedbcakDao = response.getData().get(i);
            switch (i + 1) {
                case 1: {
                    mActivityFreedbackBinding.option1RL.setVisibility(View.VISIBLE);
                    mActivityFreedbackBinding.feedtext1.setText(feedbcakDao.getFeed_back_type());
                    BindingUtils.setImageUrl(mActivityFreedbackBinding.image1, AppConstants.IMAGEPATH + feedbcakDao.getFeed_back_path());
                    int finalI = i;
                    mActivityFreedbackBinding.option1RL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentSelection = feedbcakDao.getFeed_back_ID();
                            onFeedbackClick(finalI);
//                            suggestionAdapter.addItems(feedbcakDao.getFeed_back_question(), 2);
                            setFLowLayout(feedbcakDao.getFeed_back_question());
                            mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
                case 2: {
                    mActivityFreedbackBinding.option2RL.setVisibility(View.VISIBLE);
                    mActivityFreedbackBinding.feedtext2.setText(feedbcakDao.getFeed_back_type());
                    BindingUtils.setImageUrl(mActivityFreedbackBinding.image2, AppConstants.IMAGEPATH + feedbcakDao.getFeed_back_path());
                    int finalI = i;
                    mActivityFreedbackBinding.option2RL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentSelection = feedbcakDao.getFeed_back_ID();
                            onFeedbackClick(finalI);
//                            suggestionAdapter.addItems(feedbcakDao.getFeed_back_question(), 2);
                            setFLowLayout(feedbcakDao.getFeed_back_question());
                            mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
                case 3: {
                    mActivityFreedbackBinding.option3RL.setVisibility(View.VISIBLE);
                    mActivityFreedbackBinding.feedtext3.setText(feedbcakDao.getFeed_back_type());
                    int finalI = i;
                    BindingUtils.setImageUrl(mActivityFreedbackBinding.image3, AppConstants.IMAGEPATH + feedbcakDao.getFeed_back_path());
                    mActivityFreedbackBinding.option3RL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentSelection = feedbcakDao.getFeed_back_ID();
                            onFeedbackClick(finalI);
//                            suggestionAdapter.addItems(feedbcakDao.getFeed_back_question(), 2);
                            setFLowLayout(feedbcakDao.getFeed_back_question());
                            mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
                case 4: {
                    mActivityFreedbackBinding.option4RL.setVisibility(View.VISIBLE);
                    mActivityFreedbackBinding.feedtext4.setText(feedbcakDao.getFeed_back_type());
                    int finalI = i;
                    BindingUtils.setImageUrl(mActivityFreedbackBinding.image4, AppConstants.IMAGEPATH + feedbcakDao.getFeed_back_path());
                    mActivityFreedbackBinding.option4RL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentSelection = feedbcakDao.getFeed_back_ID();
                            onFeedbackClick(finalI);
//                            suggestionAdapter.addItems(feedbcakDao.getFeed_back_question(), 2);
                            setFLowLayout(feedbcakDao.getFeed_back_question());
                            mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
                case 5: {
                    mActivityFreedbackBinding.option5RL.setVisibility(View.VISIBLE);
                    mActivityFreedbackBinding.feedtext5.setText(feedbcakDao.getFeed_back_type());
                    int finalI = i;
                    BindingUtils.setImageUrl(mActivityFreedbackBinding.image5, AppConstants.IMAGEPATH + feedbcakDao.getFeed_back_path());
                    mActivityFreedbackBinding.option5RL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentSelection = feedbcakDao.getFeed_back_ID();
                            onFeedbackClick(finalI);
//                            suggestionAdapter.addItems(feedbcakDao.getFeed_back_question(), 2);
                            setFLowLayout(feedbcakDao.getFeed_back_question());
                            mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;

            }
        }
    }

    private void setFLowLayout(List<FeedBackResponse.SubDao> items) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setSelected(false);

        }
        mActivityFreedbackBinding.flowLayoutFl.removeAllViews();
        feedback = new HashSet<>();
        for (FeedBackResponse.SubDao subDao : items) {
            View child = getLayoutInflater().inflate(R.layout.item_tags, null);
            TextviewRegular textView = child.findViewById(R.id.cbdepartment);
            textView.setText(subDao.getValue());
            if (subDao.isSelected()) {
                feedback.add(subDao.getId());
                textView.setSelected(subDao.isSelected());
            } else {
                feedback.remove(subDao.getId());
                textView.setSelected(subDao.isSelected());
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (subDao.isSelected()) {
                        subDao.setSelected(false);
                        feedback.remove(subDao.getId());
                    } else {
                        subDao.setSelected(true);
                        feedback.add(subDao.getId());
                    }
                    textView.setSelected(subDao.isSelected());
                }
            });
            mActivityFreedbackBinding.flowLayoutFl.addView(child);
        }

    }

    @Override
    public void onFeedbackClick(int question) {
        mActivityFreedbackBinding.detailSV.setVisibility(View.GONE);
        question = question + 1;
        mActivityFreedbackBinding.option1RL.setSelected(false);
        mActivityFreedbackBinding.option2RL.setSelected(false);
        mActivityFreedbackBinding.option3RL.setSelected(false);
        mActivityFreedbackBinding.option4RL.setSelected(false);
        mActivityFreedbackBinding.option5RL.setSelected(false);

        switch (question) {
            case 1:
                mActivityFreedbackBinding.option1RL.setSelected(true);
                break;
            case 2:
                mActivityFreedbackBinding.option2RL.setSelected(true);
                break;
            case 3:
                mActivityFreedbackBinding.option3RL.setSelected(true);
                break;
            case 4:
                mActivityFreedbackBinding.option4RL.setSelected(true);
                break;
            case 5:
                mActivityFreedbackBinding.option5RL.setSelected(true);
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityFreedbackBinding = getViewDataBinding();
        feedBackViewModel.setNavigator(this);
        setUp();
    }

    private void setUp() {
        String logoPath = getViewModel().getDataManager().getCurrentUserProfilePicUrl();

        BindingUtils.setImageUrl(mActivityFreedbackBinding.companyLogo, AppConstants.IMAGEPATH + logoPath);

        mActivityFreedbackBinding.flowLayoutFl.removeAllViews();
        suggestionAdapter.setResponseListener(this);
        mActivityFreedbackBinding.option1RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option2RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option3RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option4RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option5RL.setVisibility(View.GONE);
//
////        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mActivityFreedbackBinding.departmentRecycleView.setLayoutManager(gridLayoutManager);
//        mActivityFreedbackBinding.departmentRecycleView.setItemAnimator(new DefaultItemAnimator());
//        mActivityFreedbackBinding.departmentRecycleView.setAdapter(suggestionAdapter);
    }


    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSubmit() {
        if (currentSelection != null && currentSelection.length() > 0) {
            comment = mActivityFreedbackBinding.commentEt.getText().toString();
            mobile = mActivityFreedbackBinding.mobileEt.getText().toString();
            emailId = mActivityFreedbackBinding.emailIdEt.getText().toString();

            if (mobile != null && mobile.length() > 0) {
                if (mobile.length() < 10) {
                    DialogUtils.showAlertDialog(FeedBackActivity.this, "Enter proper 10 digit number. ");
                    return;
                }
            }
            if (emailId != null && emailId.length() > 0) {
                if (!ValidationUtils.isValidEmailId(emailId)) {
                    DialogUtils.showAlertDialog(FeedBackActivity.this, "Enter correct Email Id");
                    return;
                }
            }
            List<String> feedbacksublist = new ArrayList<>(feedback);
            feedBackViewModel.getSubmitFeedback(currentSelection, feedbacksublist, "", "", "");
        } else {
            Utility.ShowToast("Select one Option", this);
        }

    }

    private void getOtherDetails() {


    }


    public void showFinalSubmitDialog() {
        final Dialog dialog = new Dialog(FeedBackActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_submit);
        dialog.setCancelable(false);

        EditextRegular commentEt = dialog.findViewById(R.id.commentEt);
        EditextRegular mobileEt = dialog.findViewById(R.id.mobileEt);
        EditextRegular emailIdEt = dialog.findViewById(R.id.emailIdEt);

        Button btnSubmit = dialog.findViewById(R.id.ok_btn);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentEt.getText().toString();
                String mobile = mobileEt.getText().toString();
                String emailId = emailIdEt.getText().toString();

                if (mobile != null && mobile.length() > 0) {
                    if (mobile.length() < 10) {
                        DialogUtils.showAlertDialog(FeedBackActivity.this, "Enter proper 10 digit number. ");
                        return;
                    }
                }
                if (emailId != null && emailId.length() > 0) {
                    if (!ValidationUtils.isValidEmailId(emailId)) {
                        DialogUtils.showAlertDialog(FeedBackActivity.this, "Enter correct Email Id");
                        return;
                    }
                }

                {
                    dialog.dismiss();
                    List<String> feedbacksublist = new ArrayList<>(feedback);
                    feedBackViewModel.getSubmitFeedback(currentSelection, feedbacksublist, comment, mobile, emailId);
                }
            }
        });

        dialog.show();
    }

    @Override
    public void onSuccessSubmit(FeedBackResponse response) {
        mActivityFreedbackBinding.commentEt.setText("");
        mActivityFreedbackBinding.mobileEt.setText("");
        mActivityFreedbackBinding.emailIdEt.setText("");
        Intent intent = ThankyouActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void onLeaveComment() {
        mActivityFreedbackBinding.detailSV.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCancel() {
        mActivityFreedbackBinding.detailSV.setVisibility(View.GONE);
        mActivityFreedbackBinding.commentEt.setText("");
        mActivityFreedbackBinding.mobileEt.setText("");
        mActivityFreedbackBinding.emailIdEt.setText("");
    }

    private List<String> getFilterList(List<FeedBackResponse.SubDao> items) {
        List<String> subDaos = new ArrayList<>();
        for (FeedBackResponse.SubDao subDao : items) {
            if (subDao.isSelected()) {
                subDaos.add(subDao.getId());
            }
        }
        return subDaos;
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onRemoveObj(FeedBackResponse.SubDao generic) {

    }

    @Override
    public void onAddObj(FeedBackResponse.SubDao generic) {

    }
}
