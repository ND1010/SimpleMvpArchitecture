package ndtech.app.com.simplemvparchitecture.apihelper;

import android.annotation.SuppressLint;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.greenrobot.eventbus.EventBus;

import java.net.SocketTimeoutException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ndtech.app.com.simplemvparchitecture.BuildConfig;
import ndtech.app.com.simplemvparchitecture.application.SimpleApp;
import ndtech.app.com.simplemvparchitecture.Baseframework.model.APIError;
import ndtech.app.com.simplemvparchitecture.Baseframework.model.GenericModel;
import ndtech.app.com.simplemvparchitecture.model.LoginRequest;
import ndtech.app.com.simplemvparchitecture.model.LoginResponse;
import ndtech.app.com.simplemvparchitecture.utils.Utility;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by techno on 29/11/18.
 */

public class RestApiImpl {
    EventBus mEventBus;
    private static Retrofit retrofit;
    private RestApi mRestApi;

    public RestApiImpl(EventBus mEventBus) {
        this.mEventBus = mEventBus;
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(SimpleApp.getClient())
                .build();

        mRestApi = retrofit.create(RestApi.class);
    }


    @SuppressLint("CheckResult")
    public void doLogin(LoginRequest reqString) {

        Flowable<GenericModel<LoginResponse>> getusers = mRestApi.doLogin(Utility.getRequest(SimpleApp.getGsonWithExpose().toJson(reqString)));
        getusers.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(throwable -> {
                    try {
                        String message = ((HttpException) throwable).response().errorBody().string();
                        GenericModel<LoginResponse> userBaseResponse = SimpleApp.getGsonWithExpose().fromJson(message, GenericModel.class);
                        return userBaseResponse;
                    } catch (SocketTimeoutException e) {
                        GenericModel<LoginResponse> asd = new GenericModel<>();
                        asd.setStatus(100);
                        asd.setMessage("Server not responding");
                        return asd;
                    } catch (Exception e) {
                        GenericModel<LoginResponse> asd = new GenericModel<>();
                        asd.setStatus(100);
                        asd.setMessage("Something went wrong");
                        return asd;
                    }
                })
                .subscribe(responsData -> {
                    if (responsData != null) {
                        GenericModel<LoginResponse> responseGenericModel = responsData;
                        if ((responseGenericModel.getStatus() == 200) && (responseGenericModel.getResponse() != null)) {
                            LoginResponse loginResponse = responseGenericModel.getResponseModel(LoginResponse.class);
                            loginResponse.setMessage(responseGenericModel.getMessage());
                            mEventBus.post(loginResponse);
                        } else {
                            mEventBus.post(new APIError(105, responseGenericModel.getMessage()));
                        }
                    }
                });
    }


    /*@SuppressLint("CheckResult")
    public void getOrderHistory(CustomerListRequest reqString) {

        Flowable<GenericModel<CustomerListResponse>> getOrderHistory = mRestApis.getCustomerList(Utility.getRequest(HobbsRepairApp.getGsonWithExpose().toJson(reqString)));

        getOrderHistory.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> mEventBus.post(throwable))
                .onErrorReturn(throwable -> {
                    try {
                        String message = ((HttpException) throwable).response().errorBody().string();
                        GenericModel<CustomerListResponse> userBaseResponse = HobbsRepairApp.getGsonWithExpose().fromJson(message, GenericModel.class);
                        return userBaseResponse;
                    } catch (SocketTimeoutException e) {
                        GenericModel<CustomerListResponse> asd = new GenericModel<>();
                        asd.setStatus(100);
                        asd.setMessage("Server not responding");
                        return asd;
                    } catch (Exception e) {
                        GenericModel<CustomerListResponse> asd = new GenericModel<>();
                        asd.setStatus(100);
                        asd.setMessage("Something went wrong");
                        return asd;
                    }
                }).subscribe(response -> {

                    if (response != null) {

                        GenericModel<CustomerListResponse> responseGenericModel = response;
                        if ((responseGenericModel.getStatus() == 200) && (responseGenericModel.getResponse() != null)) {
                            CustomerListResponse customerListResponse = responseGenericModel.getResponseModel(CustomerListResponse.class);
                            mEventBus.postSticky(customerListResponse);
                        } else if ((responseGenericModel.getStatus() == 400) && (responseGenericModel.getResponse() != null)) {
                            CustomerListResponse customerListResponse = new CustomerListResponse();
                            mEventBus.postSticky(customerListResponse);
                        } else {
                            mEventBus.postSticky(new APIError(105, responseGenericModel.getMessage()));
                        }
                    }
                }
        );
    }*/

}
