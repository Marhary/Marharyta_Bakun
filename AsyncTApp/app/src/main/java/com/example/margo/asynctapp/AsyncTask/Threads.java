package com.example.margo.asynctapp.AsyncTask;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threads {

    public static final int COUNT_CORE = 3;
    private final ExecutorService executorService;

    public Threads() {
        this.executorService = Executors.newFixedThreadPool(COUNT_CORE);
    }

    public<Params, Progress, Result> void execute(final Operation<Params, Progress, Result> operation, final Params param, final IResult<Result, Progress> onResultCallback) {
        final Handler handler = new Handler();
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    final Result result = operation.doing(param, new IProgress<Progress>() {

                        @Override
                        public void onProgressUpdate(Progress progress) {

                        }

                        @Override
                        public void onProgressChanged(final Progress progress) {
                            handler.post(new Runnable() {

                                @Override
                                public void run() {
                                    onResultCallback.onProgressChanged(progress);
                                }
                            });
                        }
                    });
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            onResultCallback.onSuccess(result);
                        }
                    });
                } catch (Exception e) {
                    onResultCallback.onError(e);
                }
            }
        });
    }

}

