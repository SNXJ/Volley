package com.snxj.volley.model;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */

public class UserModel {
    private boolean rtState;
    private String rtMsg;
    private UserBean rtData;

    public boolean isRtState() {
        return rtState;
    }

    public void setRtState(boolean rtState) {
        this.rtState = rtState;
    }

    public String getRtMsg() {
        return rtMsg;
    }

    public void setRtMsg(String rtMsg) {
        this.rtMsg = rtMsg;
    }

    public UserBean getRtData() {
        return rtData;
    }

    public void setRtData(UserBean rtData) {
        this.rtData = rtData;
    }
}
