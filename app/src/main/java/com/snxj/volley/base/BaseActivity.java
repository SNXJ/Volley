package com.snxj.volley.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.snxj.volley.untils.MyActivityManager;
/**
 * @author Sheng XiaoJie
 * @date   2016/12/2
 * @desc
 */
public abstract class BaseActivity extends FragmentActivity {
    private static Activity mForegroundActivity = null;
    public BaseApplication baseApplication;

    public Bundle savedInstanceState;

    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyActivityManager.getInstance().pushOneActivity(this);

        baseApplication = (BaseApplication) getApplication();

        //底部返回虚拟键盘一直不显示
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.setAttributes(params);

        initUI();
    }

    /**
     * 初始化UI
     */
    protected abstract void initUI();

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity = this;
    }

    @Override
    protected void onPause() {
        this.mForegroundActivity = null;
        super.onPause();
    }

    /**
     * 隐藏键盘
     */
    protected void hideInputSoft() {
        if (getCurrentFocus() != null
                && getCurrentFocus().getWindowToken() != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            hideInputSoft();
        }
        return super.onTouchEvent(event);
    }
}
