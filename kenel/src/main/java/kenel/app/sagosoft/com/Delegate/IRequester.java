package kenel.app.sagosoft.com.Delegate;

/**
 * Created by FRED on 2015/12/1.
 */
public interface IRequester {
    void onSuccess(Object...object);

    void onFailure(Object...object);


}
