package ndtech.app.com.simplemvparchitecture.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import ndtech.app.com.simplemvparchitecture.apihelper.RequestInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by techno on 29/11/18.
 */

public class SimpleApp extends Application {
    //Instance of Application Class
    private static SimpleApp mInstance;

    //Instance of Gson
    private static Gson mGson;

    //Instance of Gson without Expose
    private static Gson mGsonExcludeExpose;

    //Instance of OkHttpclient
    private static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * Method for get current instance of Application
     *
     * @return
     */
    public static SimpleApp getAppInstance() {
        return mInstance;
    }

    /**
     * Method for get synchronized instance of Application
     *
     * @return
     */
    public static synchronized SimpleApp getInstance() {
        return mInstance;
    }

    /**
     * Method for Create OkHttpClient
     *
     * @return
     */
    public static OkHttpClient getClient() {

        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            RequestInterceptor m1RequestInterceptor = new RequestInterceptor();
            client = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .addInterceptor(m1RequestInterceptor)
                    .build();
        }
        return client;
    }

    /**
     * Method for get data in Preference
     *
     * @param name
     * @return
     */
    public static SharedPreferences getSharedPreference(String name) {
        SharedPreferences preferences = getAppInstance().getSharedPreferences(name, Context.MODE_PRIVATE);
        return preferences;
    }

    /**
     * Method for get SharedPreferences for edit
     *
     * @param name
     * @return
     */
    public static SharedPreferences.Editor getSharedPreferenceEditor(String name) {
        SharedPreferences preferences = getAppInstance().getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        return editor;
    }

    /**
     * Method for Gson With Expose
     *
     * @return
     */
    public static Gson getGsonWithExpose() {
        if (mGson != null) {
            mGson = null;
        }
        mGson = new GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create();
        return mGson;
    }

    /**
     * Method for Gson Without Expose
     *
     * @return
     */
    public static Gson getGsonWithoutExpose() {

        if (mGsonExcludeExpose != null) {
            mGsonExcludeExpose = null;
        }
        mGsonExcludeExpose = new GsonBuilder().setLenient().create();
        return mGsonExcludeExpose;
    }

    /**
     * Reset OkHttpclient
     */
    public static void resetClient() {
        client = null;
        getClient();
    }
}
