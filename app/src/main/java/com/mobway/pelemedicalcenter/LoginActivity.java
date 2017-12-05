package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobway.pelemedicalcenter.controllers.PatientController;
import com.mobway.pelemedicalcenter.models.UserRequest;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int VIEW_SIGN_IN = 0;
    public static final int VIEW_SIGN_UP = 1;
    public static final int VIEW_FORGOT_PWD = 2;
    public static final int VIEW_CLOSE = 3;

    private TextView mMainTitle;
    private View mButtonClose;
    private View mButtonSignIn;
    private View mButtonSignUp;
    private View mButtonForgot;
    private View mButtonSignUpSave;
    private View mButtonForgotSave;
    private View mViewSignIn;
    private View mViewSignUp;
    private View mViewForgot;

    private String mName;
    private String mEmail;
    private String mPwd;
    private PatientController mPatientController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPatientController = new PatientController(this);

        mMainTitle = findViewById(R.id.tv_title);

        mButtonClose = findViewById(R.id.button_close);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mButtonSignUp = findViewById(R.id.button_sign_up);
        mButtonForgot = findViewById(R.id.button_forgot_password);

        mButtonSignUpSave = findViewById(R.id.button_register);
        mButtonForgotSave = findViewById(R.id.button_recover);

        mViewSignIn = findViewById(R.id.content_sign_in);
        mViewSignUp = findViewById(R.id.content_sign_up);
        mViewForgot = findViewById(R.id.content_forgot);

        mButtonClose.setOnClickListener(this);
        mButtonSignIn.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);
        mButtonForgot.setOnClickListener(this);
        mButtonSignUpSave.setOnClickListener(this);
        mButtonForgotSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                if (validateSignIn()) {
                    doSignIn();
                }
                break;
            case R.id.button_sign_up:
                doSignUp();
                break;
            case R.id.button_forgot_password:
                doForgotPassword();
                break;
            case R.id.button_close:
                doClose();
                break;
            case R.id.button_register:
                if (validateSignUp()) {
                    mPatientController.create(new UserRequest(mName, mEmail, mPwd));
                }
                break;
            case R.id.button_recover:
                validateSignForgot();
                break;
        }
    }

    private void doSignIn() {
        mMainTitle.setText(changeTitle(VIEW_SIGN_IN));
        mPatientController.login(new UserRequest(mEmail, mPwd));
    }

    private void doSignUp() {
        mMainTitle.setText(changeTitle(VIEW_SIGN_UP));
        mButtonClose.setVisibility(View.VISIBLE);
        hide(mViewSignIn);
        show(mViewSignUp);
    }

    private void doForgotPassword() {
        mMainTitle.setText(changeTitle(VIEW_FORGOT_PWD));
        mButtonClose.setVisibility(View.VISIBLE);
        hide(mViewSignIn);
        show(mViewForgot);
    }

    private void doClose() {
        mMainTitle.setText(changeTitle(VIEW_CLOSE));
        mButtonClose.setVisibility(View.GONE);
        hide(mViewSignUp);
        hide(mViewForgot);
        show(mViewSignIn);
    }

    public String changeTitle(int viewType) {
        String title = "";
        switch (viewType) {
            case VIEW_SIGN_IN:
                title = "Login";
                break;
            case VIEW_SIGN_UP:
                title = "Cadastre-se";
                break;
            case VIEW_FORGOT_PWD:
                title = "Recuperação de senha";
                break;
            case VIEW_CLOSE:
                title = "Login";
                break;
        }
        return title;
    }

    public String changeView(int viewType) {
        String title = "";
        switch (viewType) {
            case VIEW_SIGN_IN:
                title = "Login";
                break;
            case VIEW_SIGN_UP:
                title = "Cadastre-se";
                break;
            case VIEW_FORGOT_PWD:
                title = "Esqueceu sua senha?";
                break;
            case VIEW_CLOSE:

                break;
        }
        return title;
    }

    public void hide(View v) {
        v.setVisibility(View.GONE);
    }

    public void show(View v) {
        v.setVisibility(View.VISIBLE);
    }

    public boolean validateSignIn() {
        TextInputLayout tilEmail = findViewById(R.id.til_email);
        TextInputEditText inputEmail = findViewById(R.id.input_email);
        TextInputLayout tilSenha = findViewById(R.id.til_pwd);
        TextInputEditText inputSenha = findViewById(R.id.input_pwd);
        if (validateEmptyInput(tilEmail, inputEmail) && validateEmptyInput(tilSenha, inputSenha)) {
            mEmail = inputEmail.getText().toString();
            mPwd = inputSenha.getText().toString();
            return true;
        } else {
            mEmail = null;
            mPwd = null;
            return false;
        }
    }

    public boolean validateSignUp() {
        TextInputLayout tilName = findViewById(R.id.til_signup_name);
        TextInputEditText inputName = findViewById(R.id.input_signup_name);

        TextInputLayout tilEmail = findViewById(R.id.til_signup_email);
        TextInputEditText inputEmail = findViewById(R.id.input_signup_email);

        TextInputLayout tilSenha = findViewById(R.id.til_signup_pwd);
        TextInputEditText inputSenha = findViewById(R.id.input_signup_pwd);

        if (validateEmptyInput(tilName, inputName) && validateEmptyInput(tilEmail, inputEmail) && validateEmptyInput(tilSenha, inputSenha)) {
            mName = inputName.getText().toString();
            mEmail = inputEmail.getText().toString();
            mPwd = inputSenha.getText().toString();
            return true;
        } else {
            mName = null;
            mEmail = null;
            mPwd = null;
            return false;
        }
    }

    public boolean validateSignForgot() {
        return false;
    }

    private boolean validateEmptyInput(TextInputLayout layout, TextInputEditText input) {
        if (input.getText().toString().equals("")) {
            layout.setBackground(getDrawable(R.drawable.bg_button_error));
//            layout.setErrorEnabled(true);
//            layout.setError("some error..");
            return false;
        }
        return true;
    }

}
