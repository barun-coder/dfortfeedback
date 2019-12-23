
package com.displayfort.feedback.ui.feedback;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.displayfort.feedback.BR;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.data.local.prefs.AppPreferencesHelper;
import com.displayfort.feedback.data.model.api.ApiError;
import com.displayfort.feedback.data.model.api.response.FeedBackResponse;
import com.displayfort.feedback.data.model.api.response.LangugeResponse;
import com.displayfort.feedback.databinding.ActivityFreedbackBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.login.LicenseLoginActivity;
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

//https://rajeshandroiddeveloper.blogspot.com/2013/07/android-popupwindow-example-in-listview.html
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
    private List<LangugeResponse.LangugeDao> languageList;
    private PopupWindow popupWindowDogs;
    public static String defaultLang = "en";
    private static String default_text = "English";

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
        if (totalcount > 0) {
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
                                mActivityFreedbackBinding.feedbackHeader.setText(feedbcakDao.getFeedback_type_header());
                                setFLowLayout(feedbcakDao.getFeed_back_question());
                                showButtonVisibile(true);

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
                                mActivityFreedbackBinding.feedbackHeader.setText(feedbcakDao.getFeedback_type_header());
                                setFLowLayout(feedbcakDao.getFeed_back_question());
                                showButtonVisibile(true);
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
                                mActivityFreedbackBinding.feedbackHeader.setText(feedbcakDao.getFeedback_type_header());
                                setFLowLayout(feedbcakDao.getFeed_back_question());
                                showButtonVisibile(true);
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
                                mActivityFreedbackBinding.feedbackHeader.setText(feedbcakDao.getFeedback_type_header());
                                setFLowLayout(feedbcakDao.getFeed_back_question());
                                showButtonVisibile(true);
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
                                mActivityFreedbackBinding.feedbackHeader.setText(feedbcakDao.getFeedback_type_header());
                                setFLowLayout(feedbcakDao.getFeed_back_question());
                                showButtonVisibile(true);
                            }
                        });
                    }
                    break;
                }
            }
        } else {
            mActivityFreedbackBinding.flowLayoutFl.removeAllViews();
            suggestionAdapter.setResponseListener(this);
            mActivityFreedbackBinding.option1RL.setVisibility(View.GONE);
            mActivityFreedbackBinding.option2RL.setVisibility(View.GONE);
            mActivityFreedbackBinding.option3RL.setVisibility(View.GONE);
            mActivityFreedbackBinding.option4RL.setVisibility(View.GONE);
            mActivityFreedbackBinding.option5RL.setVisibility(View.GONE);
        }
    }

    private void showButtonVisibile(boolean b) {
        mActivityFreedbackBinding.submitDetail.setVisibility(View.VISIBLE);
        mActivityFreedbackBinding.shareTv.setVisibility(View.VISIBLE);
    }

    private void setFLowLayout(List<FeedBackResponse.SubDao> items) {
        mActivityFreedbackBinding.feedbackHeader.setVisibility(View.VISIBLE);
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivityFreedbackBinding = getViewDataBinding();
        feedBackViewModel.setNavigator(this);
        mActivityFreedbackBinding.detailSV.setVisibility(View.GONE);
        setUp();
        setLayoutHW();

    }

    private void setLayoutHW() {
        DisplayMetrics displayMetrics = Utility.getDisplayMetrix(this);
        int height = displayMetrics.heightPixels;
        int margin = (int) Utility.convertDpToPixel(20, this);
        int width = (int) (displayMetrics.widthPixels - ((margin * 12) - (2 * getResources().getDimension(R.dimen.layout_margin))));
        int finalwidth = width / 5;
        mActivityFreedbackBinding.image1.setLayoutParams(new RelativeLayout.LayoutParams(finalwidth, finalwidth));
        mActivityFreedbackBinding.image2.setLayoutParams(new RelativeLayout.LayoutParams(finalwidth, finalwidth));
        mActivityFreedbackBinding.image3.setLayoutParams(new RelativeLayout.LayoutParams(finalwidth, finalwidth));
        mActivityFreedbackBinding.image4.setLayoutParams(new RelativeLayout.LayoutParams(finalwidth, finalwidth));
        mActivityFreedbackBinding.image5.setLayoutParams(new RelativeLayout.LayoutParams(finalwidth, finalwidth));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityFreedbackBinding.selectLanguage.setText(default_text);
        feedBackViewModel.getFeedbackQuestion(defaultLang);
    }

    private void setUp() {
        String header = getViewModel().getDataManager().getValue(AppPreferencesHelper.PREF_KEY_HEADER_TEXT);
        String subHeader = getViewModel().getDataManager().getValue(AppPreferencesHelper.PREF_KEY_SUB_HEADER_TEXT);
        String logoPath = getViewModel().getDataManager().getCurrentUserProfilePicUrl();

        mActivityFreedbackBinding.subheading.setText(subHeader);
        String h1 = "", h2 = "";

        if (header.contains(" ")) {
            String[] splitCount = header.split(" ");
//            if(splitCount.length<=2) {
            String[] splittext = header.split(" ", 2);
            h1 = splittext[0] + " ";
            h2 = splittext[1];

        } else {
            int dlt = header.length() / 2;
            h1 = header.substring(0, dlt);
            h2 = header.substring(dlt, header.length());
        }

        String text = "<font color=#F75666>" + h1 + "</font><font color=#000000>" + h2 + "</font>";
        mActivityFreedbackBinding.heading.setText(Html.fromHtml(text));

        BindingUtils.setImageUrl(mActivityFreedbackBinding.companyLogo, AppConstants.IMAGEPATH + logoPath);

        mActivityFreedbackBinding.flowLayoutFl.removeAllViews();
        suggestionAdapter.setResponseListener(this);
        mActivityFreedbackBinding.option1RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option2RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option3RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option4RL.setVisibility(View.GONE);
        mActivityFreedbackBinding.option5RL.setVisibility(View.GONE);
    }


    @Override
    public void openLoginActivity() {
        Intent intent = LicenseLoginActivity.newIntent(this);
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
            feedBackViewModel.getSubmitFeedback(currentSelection, feedbacksublist, comment, mobile, emailId);
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
//        scrollToView(mActivityFreedbackBinding.detailSV);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivityFreedbackBinding.detailSV.getParent().requestChildFocus(mActivityFreedbackBinding.detailSV, mActivityFreedbackBinding.detailSV);
            }
        }, 300);

    }

    private void scrollToView(final View view) {
        // Get deepChild Offset
        NestedScrollView scrollViewParent = mActivityFreedbackBinding.scrollview;
        Point childOffset = new Point();
        getDeepChildOffset(scrollViewParent, view.getParent(), view, childOffset);
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y);
    }

    private void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();
        if (parentGroup.equals(mainParent)) {
            return;
        }
        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }

    @Override
    public void onCancel() {
        mActivityFreedbackBinding.detailSV.setVisibility(View.GONE);
        mActivityFreedbackBinding.commentEt.setText("");
        mActivityFreedbackBinding.mobileEt.setText("");
        mActivityFreedbackBinding.emailIdEt.setText("");
    }

    @Override
    public void onChangeLang() {
        if (languageList != null && languageList.size() > 0) {
            popupWindowDogs.showAsDropDown(mActivityFreedbackBinding.selectLangLL, -5, 0);
        }

    }

    @Override
    public void showLanguageList(LangugeResponse response) {
        languageList = response.getData();
        languageList.add(0, new LangugeResponse.LangugeDao("en", "English"));
        popupWindowDogs = popupWindowDogs();
    }

    public PopupWindow popupWindowDogs() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewDogs = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewDogs.setAdapter(dogsAdapter(languageList));

        // set the item click listener
        listViewDogs.setOnItemClickListener(new DogsDropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(450);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setBackgroundDrawable(getDrawable(R.color.white));
        } else {
            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.white));
        }
        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);

        return popupWindow;
    }

    private ArrayAdapter dogsAdapter(List<LangugeResponse.LangugeDao> dogsArray) {

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.layout_poupul_view, dogsArray) {

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_poupul_view, parent, false);
                }
// setting the ID and text for every items in the list
                LangugeResponse.LangugeDao text = (LangugeResponse.LangugeDao) getItem(position);
// visual settings for the list item
                TextView listItem = convertView.findViewById(R.id.text);
                listItem.setText(text.getLang_name());
                return convertView;
            }
        };
        return adapter;
    }

    private void StartOnLangSelect(LangugeResponse.LangugeDao text) {
        defaultLang = text.getLang_code();
        default_text = text.getLang_name();
        mActivityFreedbackBinding.selectLanguage.setText(default_text);
        feedBackViewModel.getFeedbackQuestion(defaultLang);
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

    public class DogsDropdownOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {

            // get the context and main activity to access variables
            Context mContext = v.getContext();
            FeedBackActivity mainActivity = ((FeedBackActivity) mContext);

            // add some animation when a list item was clicked
            Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
            fadeInAnimation.setDuration(10);
            v.startAnimation(fadeInAnimation);

            // dismiss the pop up
            mainActivity.popupWindowDogs.dismiss();

            StartOnLangSelect(languageList.get(arg2));

            // get the text and set it as the button text


        }

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
