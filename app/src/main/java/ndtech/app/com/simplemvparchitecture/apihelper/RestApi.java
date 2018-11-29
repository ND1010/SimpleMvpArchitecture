package ndtech.app.com.simplemvparchitecture.apihelper;

import io.reactivex.Flowable;
import ndtech.app.com.simplemvparchitecture.Baseframework.model.GenericModel;
import ndtech.app.com.simplemvparchitecture.model.LoginResponse;
import ndtech.app.com.simplemvparchitecture.utils.Constants;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by techno on 29/11/18.
 */

public interface RestApi {

    @POST(Constants.ApiMethods.GET_LOGIN)
    Flowable<GenericModel<LoginResponse>> doLogin(@Body RequestBody requestBody);
}
