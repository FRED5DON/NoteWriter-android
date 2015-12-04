package kenel.app.sagosoft.com.EventBus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by FRED on 2015/12/1.
 */
public class EventBusBuilder {


    private static EventBusBuilder builder;

    private static Bus bus;

    /**
     * avoid the multi request at time
     *
     * @return
     */
    public static synchronized EventBusBuilder build() {
        if (builder == null) {
            builder = new EventBusBuilder();
            bus = bus();
        }
        return builder;
    }

    private static synchronized Bus bus() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.MAIN);
        }
        return bus;
    }


    /**
     * 发送事件
     * @param object
     */
    public void post(Object object) {
        bus.post(object);
    }

    /**
     * 注册
     * @param object
     */
    public void register(Object object) {
        bus.register(object);
    }


    /**
     * 取消
     * @param object
     */
    public void unregister(Object object) {
        bus.unregister(object);
    }
}
