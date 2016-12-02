package com.snxj.volley.net;

import com.android.volley.VolleyError;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */
public interface ResponseListener<T> {
     void responSuccess(T t);

     void resonError(VolleyError error);
}
