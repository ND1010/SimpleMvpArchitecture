package ndtech.app.com.simplemvparchitecture.Baseframework.baseview;

public interface BasePresenter {

    /**
     * Method that should signal the appropriate view to show the appropriate error with the
     * provided message.
     */
    void onError(String message);

    void registerBus();

    void unRegisterBus();

}
