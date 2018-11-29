package ndtech.app.com.simplemvparchitecture.mvp.presenter;

import ndtech.app.com.simplemvparchitecture.Baseframework.baseview.BasePresenter;
import ndtech.app.com.simplemvparchitecture.Baseframework.baseview.BaseView;
import ndtech.app.com.simplemvparchitecture.model.LoginResponse;
import ndtech.app.com.simplemvparchitecture.mvp.model.LoginActivityModel;

//this interface is used to get data from api or data to pass on UI using view object
public interface LoginPresenter extends BasePresenter {

    //this interface is used to setup UI after any api or db call.
    interface LoginView extends BaseView {

        void onLoginSuccessful(LoginResponse loginResponse);

        LoginActivityModel doRetrieveModel();
    }

    void doLogin();

}