package kenel.app.sagosoft.com.RestHttp;

/**
 * Created by FRED_anjujia on 2015/12/1.
 */

import java.util.Map;

import retrofit.client.Client;

/**
 * RestBuilderOptions
 */
public class RestBuilderOptions {

    public String host = "127.0.0.1";

    public IRequestInterceptor rei;

    public Client.Provider provider;

    public boolean isShowFullLog = true;

    public Map<String, String> headers;
}
