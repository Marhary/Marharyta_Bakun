package com.example.margo.asynctapp.AsyncTask;

public interface IResultObject {
    void onSuccess(Operation.Result result);

    void onError(Exception e);
}
