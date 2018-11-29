package ndtech.app.com.simplemvparchitecture.Baseframework.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ndtech.app.com.simplemvparchitecture.application.SimpleApp;


public class GenericModel<Dhruv> {

    Dhruv ResponseJson;

    @Expose
    @SerializedName("code")
    int code;

    @Expose
    @SerializedName("url")
    String url;

    @Expose
    @SerializedName("message")
    String message;

    @Expose
    @SerializedName("response")
    Object response;

    public Dhruv getResponseModel(Class<Dhruv> aModel) {
        setResponseJson(aModel);
        return ResponseJson;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponseJson(Class<Dhruv> aModel) {
        ResponseJson = prepareModel(response, aModel);
    }

    protected <T> T prepareModel(@NonNull Object aString, Class<T> aClass) {
        return SimpleApp.getGsonWithExpose().fromJson(SimpleApp.getGsonWithExpose().toJson(aString), aClass);
    }


    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }


}
