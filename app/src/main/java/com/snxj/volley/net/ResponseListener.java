package com.snxj.volley.net;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */
public interface ResponseListener<T> {
     void responSuccess(T t);

    // void resonError(VolleyError error);
}
