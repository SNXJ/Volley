package com.snxj.volley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.VolleyError;
import com.snxj.volley.model.UserModel;
import com.snxj.volley.net.NetApi;
import com.snxj.volley.net.ResponseListener;
import com.snxj.volley.untils.LogUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */
public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    ResponseListener<UserModel> responseListener = new ResponseListener<UserModel>() {
        @Override
        public void responSuccess(UserModel userModel) {
            Snackbar.make(fab,  userModel.isRtState() + userModel.getRtData().getName() + userModel.getRtData().getPhone()+"", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            LogUtils.i("+++++++++++++++++", "respon=" + userModel.isRtState() + userModel.getRtData().getName() + userModel.getRtData().getPhone());
        }

        @Override
        public void resonError(VolleyError error) {
            LogUtils.i("+++++++++++++++++", "error" + error.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                requestHTTPsTest();


            }
        });
    }

    public void requestHTTPsTest() {
        Map<String, String> paramsMap = new TreeMap<>();
        paramsMap.put("terminal", "2");//平台标示，android 为2
        paramsMap.put("phone", "17090094674");
        paramsMap.put("password", "123qwe");
        NetApi.login(MainActivity.this, "POST", NetApi.LOGIN_URL, paramsMap, UserModel.class, responseListener);
    }
}