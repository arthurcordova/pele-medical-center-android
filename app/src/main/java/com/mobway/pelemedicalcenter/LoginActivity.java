package com.mobway.pelemedicalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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

    private View mViewSignIn;
    private View mViewSignUp;
    private View mViewForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mMainTitle = findViewById(R.id.tv_title);

        mButtonClose = findViewById(R.id.button_close);
        mButtonSignIn = findViewById(R.id.button_sign_in);
        mButtonSignUp = findViewById(R.id.button_sign_up);
        mButtonForgot = findViewById(R.id.button_forgot_password);

        mViewSignIn = findViewById(R.id.content_sign_in);
        mViewSignUp = findViewById(R.id.content_sign_up);
        mViewForgot = findViewById(R.id.content_forgot);

        mButtonClose.setOnClickListener(this);
        mButtonSignIn.setOnClickListener(this);
        mButtonSignUp.setOnClickListener(this);
        mButtonForgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_sign_in:
                doSignIn();
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
        }
    }

    private void doSignIn() {
        mMainTitle.setText(changeTitle(VIEW_SIGN_IN));
        Intent it = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(it);
        finish();
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

}
