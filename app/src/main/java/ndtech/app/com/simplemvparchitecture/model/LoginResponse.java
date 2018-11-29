package ndtech.app.com.simplemvparchitecture.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ndtech.app.com.simplemvparchitecture.Baseframework.model.BaseResponse;

public class LoginResponse extends BaseResponse {

    @SerializedName("Users")
    @Expose
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}