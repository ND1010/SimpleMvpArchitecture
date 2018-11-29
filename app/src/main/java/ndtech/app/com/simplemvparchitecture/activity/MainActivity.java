package ndtech.app.com.simplemvparchitecture.activity;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ndtech.app.com.simplemvparchitecture.Baseframework.baseview.BaseActivity;
import ndtech.app.com.simplemvparchitecture.R;
import ndtech.app.com.simplemvparchitecture.model.LoginResponse;
import ndtech.app.com.simplemvparchitecture.model.UserInfo;
import ndtech.app.com.simplemvparchitecture.mvp.model.LoginActivityModel;
import ndtech.app.com.simplemvparchitecture.mvp.presenter.LoginPresenter;
import ndtech.app.com.simplemvparchitecture.mvp.presenter.LoginPresenterImpl;
import ndtech.app.com.simplemvparchitecture.utils.Utility;

public class MainActivity extends BaseActivity implements LoginPresenter.LoginView {

    String TAG = "MainActivity";
    private LoginPresenterImpl presenter;
    private LoginActivityModel model;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new LoginPresenterImpl(this);
        model = new LoginActivityModel(this);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRetrieveModel().setRequestLogin("developer.technostacks@gmail.com", "123456");
                presenter.doLogin();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.registerBus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unRegisterBus();
    }

    @Override
    public void showError(String message) {
        Utility.showSnackBar(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG, true, this);
    }

    @Override
    public void showSnackBar(String message) {
        Utility.showSnackBar(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT, true, this);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProgress() {
        Utility.showProgressDialog(this, "");
    }

    @Override
    public void hideProgress() {
        Utility.hideProgressDialog();
    }

    @Override
    public Activity getViewActivity() {
        return this;
    }

    @Override
    public void onNetworkStateChange(boolean isConnect) {

    }

    @Override
    public void onLoginSuccessful(LoginResponse loginResponse) {
        UserInfo data = loginResponse.getData();
        Log.e(TAG,"user info = "+data.toString());
    }

    @Override
    public LoginActivityModel doRetrieveModel() {
        return model;
    }
}
