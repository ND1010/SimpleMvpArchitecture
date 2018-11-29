package ndtech.app.com.simplemvparchitecture.mvp.domain;


import ndtech.app.com.simplemvparchitecture.model.LoginResponse;

public class LoginActivityDomain {

    private LoginResponse userResponse;

    public LoginResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(LoginResponse userResponse) {
        this.userResponse = userResponse;
    }

}
