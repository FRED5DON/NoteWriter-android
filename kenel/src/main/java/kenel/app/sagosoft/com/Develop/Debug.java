package kenel.app.sagosoft.com.Develop;

import android.content.Context;
import android.util.Log;

/**
 * Created by FRED on 2015/12/2.
 */
public class Debug {

    private static final String TAG = "**Debug**";
    private static final boolean isDebug=true;

    public void log(Context context,String msg){
        if(isDebug){
            Log.d(TAG, getOccurPoint()+msg);
        }

    }


    private static String getOccurPoint() {
        final String className = Debug.class.getName();
        final StackTraceElement[] traces = Thread.currentThread()
                .getStackTrace();
        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":"
                                + trace.getMethodName() + ":"
                                + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (clazz.getSimpleName()!=null && clazz.getSimpleName().length()>0) {
                return clazz.getSimpleName();
            }
            return getClassName(clazz.getEnclosingClass());
        }
        return "";
    }
}
