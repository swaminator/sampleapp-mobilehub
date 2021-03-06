package com.amazonaws.mobilehelper.auth.signin.ui.userpools;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.mobilehelper.R;
import com.amazonaws.mobilehelper.auth.signin.CognitoUserPoolsSignInProvider;

/**
 * Activity to prompt for a a verification code.
 */
public class MFAActivity extends Activity {
    /** Log tag. */
    private static final String LOG_TAG = MFAActivity.class.getSimpleName();
    private MFAView mfaView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfa);
        mfaView = (MFAView) findViewById(R.id.mfa_view);
    }

    /**
     * Retrieve input and return to caller.
     * @param view the Android View
     */
    public void verify(final View view) {
        final String verificationCode = mfaView.getMFACode();

        Log.d(LOG_TAG, "verificationCode = " + verificationCode);

        final Intent intent = new Intent();
        intent.putExtra(CognitoUserPoolsSignInProvider.AttributeKeys.VERIFICATION_CODE, verificationCode);

        setResult(RESULT_OK, intent);

        finish();
    }
}
