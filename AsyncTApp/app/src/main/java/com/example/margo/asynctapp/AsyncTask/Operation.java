package com.example.margo.asynctapp.AsyncTask;

public interface Operation<Params, Progress, Result> {

    Result doing(Params params, IProgress<Progress> progressCallback) throws Exception;

    public class Result {
    }
}

