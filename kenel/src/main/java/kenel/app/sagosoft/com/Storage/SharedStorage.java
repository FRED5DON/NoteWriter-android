package kenel.app.sagosoft.com.Storage;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by FRED_anjujia on 2015/12/1.
 */
public class SharedStorage {

    private static final String GLOBAL_SHARE_ROOT_NAME = "note.app.sagosoft.com.Storage";

    private static SharedStorage sharedStorage;

    private static SharedPreferences sharedPreferences;


    private boolean isInEditMode = false;
    private SharedPreferences.Editor editor;

    public static SharedStorage build(Context context) {
        if (sharedStorage == null) {
            sharedStorage = new SharedStorage();
            sharedPreferences = context.getSharedPreferences(GLOBAL_SHARE_ROOT_NAME, Context.MODE_PRIVATE);
        }
        return sharedStorage;
    }

    /**
     * 存值
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public synchronized <T> SharedStorage put(String key, T t) {
        if (!isInEditMode) {
            isInEditMode = true;
            editor = sharedPreferences.edit();
        }
        if (t instanceof String) {
            editor.putString(key, String.valueOf(t));
        } else if (t instanceof Integer) {
            editor.putInt(key, (Integer) t);
        } else if (t instanceof Boolean) {
            editor.putBoolean(key, (Boolean) t);
        } else if (t instanceof Long) {
            editor.putLong(key, (Long) t);
        } else {
            //抛异常
            return sharedStorage;
        }
        return sharedStorage;
    }

    /**
     * 提交
     */
    public void commit() {
        if (isInEditMode) {
            editor.commit();
            isInEditMode = false;
        }

    }

    /**
     * 取值
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    public synchronized <T> Object get(String key, T t) {
        if (t instanceof String) {
            return sharedPreferences.getString(key, "");
        }
        if (t instanceof Integer) {
            return sharedPreferences.getInt(key, 0);
        }
        if (t instanceof Boolean) {
            return sharedPreferences.getBoolean(key, false);
        }
        if (t instanceof Long) {
            return sharedPreferences.getLong(key, 0);
        }
        //抛异常
        return null;
    }


    /**
     *
     */
    public void clearAll() {
        sharedPreferences.edit().clear().commit();
    }


}
