package kenel.app.sagosoft.com.Features;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by FRED_anjujia on 2015/12/2.
 */
public class Device {

    /**
     * 获取设备显示器属性
     * @param context
     * @param windoworScreen
     * @return
     */
    public static DisplayMetrics getScreenFrame(Context context,boolean windoworScreen){
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * dp2px
     * @param context
     * @param dp
     * @return
     */
    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    /**
     * 获取设备系统SDK版本
     * @return
     */
    public static int getSDK_INT(){
        return android.os.Build.VERSION.SDK_INT;
    }


    /**
     * 获取设备系统版本号
     * @return
     */
    public static String getSDKVersion(){
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 获取手机型号
     * @return
     */
    public static String getModel(){
        return android.os.Build.MODEL;
    }


}
