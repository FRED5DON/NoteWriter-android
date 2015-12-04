package kenel.app.sagosoft.com.RestHttp;

import java.util.Iterator;
import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


/**
 * Created by FRED on 2015/12/1.
 */
public class RestBuilder {

    /**
     *
     *
     * @param options
     * @param model
     * @param <T>
     * @return
     */
    public static <T> T build(RestBuilderOptions options, Class<T> model) {
        if (options == null) {
            return null;
        }
        RestAdapter.Builder bu = new RestAdapter.Builder();

        bu.setEndpoint(options.host);
        if (options.rei != null) {
            bu.setRequestInterceptor(options.rei);
        }else{
            if (options.headers != null) {
                options.rei=new IRequestInterceptor(options.headers);
                bu.setRequestInterceptor(options.rei);
            }
        }
        if (options.provider != null) {
            bu.setClient(options.provider);
        }
        bu.setLogLevel(options.isShowFullLog?RestAdapter.LogLevel.FULL:RestAdapter.LogLevel.NONE);
        return bu.build().create(model);
    }


}


class IRequestInterceptor implements RequestInterceptor {

    private final Map<String, String> headers;

    public IRequestInterceptor(Map<String, String> headers) {
        super();
        this.headers = headers;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (headers == null) {
            return;
        }
        Iterator iter = headers.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            request.addHeader(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
    }
}
