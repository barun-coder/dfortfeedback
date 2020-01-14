
package com.displayfort.feedback.ui.login;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.displayfort.feedback.BR;
import com.displayfort.feedback.NrFtPrefrence;
import com.displayfort.feedback.R;
import com.displayfort.feedback.ViewModelProviderFactory;
import com.displayfort.feedback.data.model.api.ApiError;
import com.displayfort.feedback.data.model.api.response.LoginResponse;
import com.displayfort.feedback.databinding.ActivityLicenseLoginBinding;
import com.displayfort.feedback.ui.base.BaseActivity;
import com.displayfort.feedback.ui.base.BaseAnimation;
import com.displayfort.feedback.ui.feedback.FeedBackActivity;
import com.displayfort.feedback.utils.DialogUtils;

import javax.inject.Inject;


public class LicenseLoginActivity extends BaseActivity<ActivityLicenseLoginBinding, LicenseLoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LicenseLoginViewModel mLicenseLoginViewModel;
    private ActivityLicenseLoginBinding mActivityLicenseLoginBinding;
    private String mIdentifierId = "";

    public static Intent newIntent(Context context) {
        return new Intent(context, LicenseLoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_license_login;
    }

    @Override
    public LicenseLoginViewModel getViewModel() {
        mLicenseLoginViewModel = ViewModelProviders.of(this, factory).get(LicenseLoginViewModel.class);
        return mLicenseLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        ApiError apiError = new ApiError(((ANError) throwable).getErrorBody(), ((ANError) throwable).getErrorCode());
        DialogUtils.showAlertDialog(this, apiError.getMessage());
    }

    @Override
    public void login() {
        String identifierId = mActivityLicenseLoginBinding.identifier.getText().toString();
        String IpAddress = mActivityLicenseLoginBinding.username.getText().toString();
        String licensekey = mActivityLicenseLoginBinding.password.getText().toString();
        int validation = mLicenseLoginViewModel.isEmailAndPasswordValid(identifierId, licensekey, IpAddress);
        if (validation == 0) {
            hideKeyboard();
            new NrFtPrefrence(this).setIP_ADDRESS(IpAddress);
            mLicenseLoginViewModel.liscensreCheck(identifierId, licensekey);
        } else {
            setError(validation);
        }
    }


    private void setError(int validation) {
        switch (validation) {
            case 1:
                mActivityLicenseLoginBinding.username.setError("Please enter Unique Id");
                break;
            case 3:
                mActivityLicenseLoginBinding.username.setError("Please enter IP address");
                break;
            case 2:
                mActivityLicenseLoginBinding.password.setError("Please enter License key ");
                break;

        }
    }

    @Override
    public void OpenForgotPasswordActivity() {


    }

    @Override
    public void openMainActivity(LoginResponse.UserDao data) {
        if (data.getSub_user_id() != null && !data.getSub_user_id().equals("")) {
        Intent intent = FeedBackActivity.newIntent(LicenseLoginActivity.this);
        startActivity(intent);
        BaseAnimation.setAnimationTransition(this, BaseAnimation.EFFECT_TYPE.TAB_ZOOM);
        finish();
        } else {
            DialogUtils.showAlertDialog(this, "Account not yet complete\nPlease contact Admin for more detail");
        }
    }


    @Override
    public void OpenVirificationActivity(String message) {
        DialogUtils.showAlertDialog(this, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLicenseLoginBinding = getViewDataBinding();
        mLicenseLoginViewModel.setNavigator(this);
        mActivityLicenseLoginBinding.identifier.setVisibility(View.VISIBLE);
        mActivityLicenseLoginBinding.username.setVisibility(View.VISIBLE);
        mActivityLicenseLoginBinding.password.setHint("Enter License key");
        getUniqueNumberPermision();
        mActivityLicenseLoginBinding.password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });
    }

    private void getUniqueNumberPermision() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                RequestPermission(Manifest.permission.READ_PHONE_STATE, 102);
            } else {
                getUniqueNumber();
            }
        }

    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void RequestPermission(String permision, int code) {
        ActivityCompat.requestPermissions(this,
                new String[]{permision},
                code);
        mayRequestContacts(permision, code);
    }

    private boolean mayRequestContacts(final String permision, final int mRequestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (this.checkSelfPermission(permision) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(permision)) {
            requestPermissions(new String[]{permision}, mRequestCode);
        } else {
            requestPermissions(new String[]{permision}, mRequestCode);
        }

        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 102: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUniqueNumber();
                } else {
                    Toast.makeText(this, "Permission denied to read Phone state", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    private void getUniqueNumber() {
        try {
            String android_id = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            Log.d("UNIQUE", android_id);
            mIdentifierId = android_id;
            mActivityLicenseLoginBinding.identifier.setText(android_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
