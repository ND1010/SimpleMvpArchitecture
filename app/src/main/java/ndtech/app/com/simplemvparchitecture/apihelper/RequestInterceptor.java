package ndtech.app.com.simplemvparchitecture.apihelper;

import java.io.IOException;

import ndtech.app.com.simplemvparchitecture.application.SimpleApp;
import ndtech.app.com.simplemvparchitecture.utils.Constants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {


    private String credentials = "";

    public RequestInterceptor() {

//        if (SimpleApp.getLoggedInUser() != null) {
//            this.credentials = Credentials.basic(HobbsRepairApp.getLoggedInUser().getEmail(), HobbsRepairApp.getLoggedInUser().getApiPlainKey());
//        }
    }

    @Override
    public Response intercept(Chain aChain) throws IOException {

        Request.Builder builder = aChain.request().newBuilder();
        builder.addHeader(Constants.ApiHeaders.AUTHORIZATION, credentials);

        Request newRequest = builder.build();

//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"conditions\":{\"type\":\"letter\"}}\n");
//        RequestBody body = newRequest.body();
        Request request = builder
//                .url(BuildConfig.BASE_URL+Constants.ApiMethods.GET_ITEMS)
//                .post(body)
//                .addHeader("authorization", "Basic QUg3QkJyZWlubDo0YzA1ZWViOTdkNWM1MzZjODdlNTRhMmE4YjBhZDcxMDhjNzUyMjk5")
                .build();

        return aChain.proceed(request);
    }
}