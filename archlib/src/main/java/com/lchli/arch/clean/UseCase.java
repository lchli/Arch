package com.lchli.arch.clean;

public abstract class UseCase<P, R> {
    private static final TaskExecutor def = new DefaultTaskExecutor();

    public static void executeOnDiskIO(Runnable r) {
        def.executeOnDiskIO(r);
    }

    public static void executeOnMainThread(Runnable r) {
        def.executeOnMainThread(r);
    }

    public TaskExecutor getTaskExecutor() {
        return def;
    }

    public void invokeAsync(final P params, final ControllerCallback<R> resultCallback) {

        getTaskExecutor().executeOnDiskIO(new Runnable() {
            @Override
            public void run() {

                final ResponseValue<R> r = execute(params);

                if (resultCallback == null) {
                    return;
                }

                getTaskExecutor().executeOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (r.hasError()) {
                            resultCallback.onError(r.code, r.getErrorMsg());
                        } else {
                            resultCallback.onSuccess(r.data);
                        }
                    }
                });

            }
        });
    }


    public ResponseValue<R> invokeSync(P params) {
        return execute(params);
    }


    protected abstract ResponseValue<R> execute(P parameters);

}
