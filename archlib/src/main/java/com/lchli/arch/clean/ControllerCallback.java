package com.lchli.arch.clean;

import android.support.annotation.Nullable;

public interface ControllerCallback<DATA> {

    public abstract void onSuccess(@Nullable DATA data);

    public abstract void onError(int code, String msg);

}
