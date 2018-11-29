package ndtech.app.com.simplemvparchitecture.mvp.model;

import android.content.Context;

import ndtech.app.com.simplemvparchitecture.Baseframework.baseview.BaseViewModel;
import ndtech.app.com.simplemvparchitecture.model.LoginRequest;
import ndtech.app.com.simplemvparchitecture.mvp.domain.LoginActivityDomain;

//this class is used to set request  and get response
public class LoginActivityModel extends BaseViewModel {

    public LoginActivityDomain mainActivityDomain;
    private LoginRequest requestLogin;

    // initialization of any array for related list is done here
    public LoginActivityModel(Context context) {
        super(context);
        this.mainActivityDomain = new LoginActivityDomain();
    }

    public LoginRequest getRequestLogin() {
        return requestLogin;
    }

    public void setRequestLogin(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setEmail(username);
        request.setPassword(password);
        this.requestLogin = request;
    }
}
