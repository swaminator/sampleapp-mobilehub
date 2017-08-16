package com.amazonaws.mobilehelper.auth.signin.ui.userpools;
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.mobilehelper.R;
import com.amazonaws.mobilehelper.auth.signin.SignInManager;
import com.amazonaws.mobilehelper.auth.signin.ui.DisplayUtils;
import com.amazonaws.mobilehelper.auth.signin.ui.SplitBackgroundDrawable;
import com.amazonaws.mobilehelper.config.AWSMobileHelperConfiguration;

import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_BUTTON_COLOR;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_BUTTON_CORNER_RADIUS;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.FORM_SIDE_MARGIN_RATIO;
import static com.amazonaws.mobilehelper.auth.signin.ui.userpools.UserPoolFormConstants.MAX_FORM_WIDTH_IN_PIXELS;

/**
 * The view that handles user sign-up for Cognito User Pools.
 */
public class SignUpView extends LinearLayout {
    private TextView signUpMessage;
    private Button signUpButton;
    private FormView signUpForm;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText givenNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private SplitBackgroundDrawable splitBackgroundDrawable;

    public SignUpView(final Context context) {
        this(context, null);
    }

    public SignUpView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignUpView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

        final int backgroundColor;
        if (isInEditMode()) {
            backgroundColor = Color.DKGRAY;
        } else {
            final AWSMobileHelperConfiguration helperConfig =
                SignInManager.getInstance().getIdentityManager().getHelperConfiguration();
            backgroundColor = helperConfig.getSignInBackgroundColor(Color.DKGRAY);
        }
        splitBackgroundDrawable = new SplitBackgroundDrawable(0, backgroundColor);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        signUpForm = (FormView) findViewById(R.id.signup_form);
        userNameEditText = signUpForm.addFormField(getContext(),
            InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PERSON_NAME,
            getContext().getString(R.string.username_text));

        passwordEditText = signUpForm.addFormField(getContext(),
            InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD,
            getContext().getString(R.string.sign_in_password));

        givenNameEditText = signUpForm.addFormField(getContext(),
            InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PERSON_NAME,
            getContext().getString(R.string.given_name_text));

        emailEditText = signUpForm.addFormField(getContext(),
            InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            getContext().getString(R.string.email_address_text));

        phoneEditText = signUpForm.addFormField(getContext(),
            InputType.TYPE_CLASS_PHONE,
            getContext().getString(R.string.phone_number_text));

        signUpMessage = (TextView)findViewById(R.id.signup_message);
        signUpButton = (Button) findViewById(R.id.signup_button);
        setupSignUpButtonBackground();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int maxWidth = Math.min((int)(parentWidth * FORM_SIDE_MARGIN_RATIO), MAX_FORM_WIDTH_IN_PIXELS);
        super.onMeasure(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST), heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setupSplitBackground();
    }

    private void setupSignUpButtonBackground() {
        signUpButton.setBackgroundDrawable(DisplayUtils.getRoundedRectangleBackground(
            FORM_BUTTON_CORNER_RADIUS, FORM_BUTTON_COLOR));
        LayoutParams signUpButtonLayoutParams = (LayoutParams) signUpButton.getLayoutParams();
        signUpButtonLayoutParams.setMargins(
            signUpForm.getFormShadowMargin(),
            signUpButtonLayoutParams.topMargin,
            signUpForm.getFormShadowMargin(),
            signUpButtonLayoutParams.bottomMargin);
    }

    private void setupSplitBackground() {
        splitBackgroundDrawable.setSplitPointDistanceFromTop(
            signUpForm.getTop() + (signUpForm.getMeasuredHeight()/2));
        ((ViewGroup)getParent()).setBackgroundDrawable(splitBackgroundDrawable);
    }

    /**
     * @return the user's user name entered in the form.
     */
    public String getUserName() {
        return userNameEditText.getText().toString();
    }

    /**
     * @return the user's password entered in the form.
     */
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    /**
     * @return the user's given name entered in the form.
     */
    public String getGivenName() {
       return givenNameEditText.getText().toString();
    }

    /**
     * @return the user's email entered in the form.
     */
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    /**
     * @return the user's phone number entered in the form.
     */
    public String getPhone() {
        return phoneEditText.getText().toString();
    }
}