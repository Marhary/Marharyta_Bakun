package com.example.margo.asynctapp.AsyncTask;

public abstract class IBackground<Params, Progress, Result> {

    public abstract Result doInBackground(Params params, IProgress<Progress> progressCallback);

}

