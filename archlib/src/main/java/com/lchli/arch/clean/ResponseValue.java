package com.lchli.arch.clean;

/**
 * Created by lichenghang on 2018/1/28.
 */

public class ResponseValue<DATA> {

    public int code;
    private String errorMsg;
    public DATA data;

    private boolean hasError = false;

    public boolean hasError() {
        return hasError;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ResponseValue setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        hasError = true;
        return this;
    }

    public ResponseValue setCode(int code) {
        this.code = code;
        return this;
    }

    public ResponseValue setData(DATA data) {
        this.data = data;
        return this;
    }


}
