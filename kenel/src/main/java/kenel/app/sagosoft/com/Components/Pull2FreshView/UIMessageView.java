package kenel.app.sagosoft.com.Components.Pull2FreshView;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by FRED_anjujia on 2015/12/2.
 */
public class UIMessageView extends Toast {

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public UIMessageView(Context context) {
        super(context);
    }

    /**
     * 系统默认
     * @param context
     * @param msg
     */
    public static void showMessage(Context context,String msg){
        UIMessageView.makeText(context,msg,UIMessageView.LENGTH_LONG).show();
    }

    /**
     * 自定义的View
     * @param context
     * @param msg
     * @param view
     */
    public static void showFDMessage(Context context,String msg,View view){
        UIMessageView result = new UIMessageView(context);
        result.setView(view);
        result.setDuration(UIMessageView.LENGTH_LONG);
        result.show();
    }
}
