package com.prosupport.lolchampions.async;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import com.prosupport.lolchampions.data.Champion;
import com.prosupport.lolchampions.datamanager.ChampionsManager;
import com.prosupport.lolchampions.listeners.OnChampionsReadyListener;

import java.util.List;

public class DataLoaderAsyncTask extends AsyncTask<Context, Void, List<Champion>>{

    private OnChampionsReadyListener mOnChampionsReadyListener;

    public DataLoaderAsyncTask(OnChampionsReadyListener listener) {
        this.mOnChampionsReadyListener = listener;
    }

    @Override
    protected List<Champion> doInBackground(Context... ctx) {
        return ChampionsManager.getInstance(ctx[0]).getChampions();
    }

    @Override
    protected void onPostExecute(List<Champion> champions) {
        super.onPostExecute(champions);
        notifyChampionsReady(champions);
    }

    private void notifyChampionsReady(final List<Champion> champions) {
        if (mOnChampionsReadyListener != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mOnChampionsReadyListener.onReady(champions);
                }
            });
        }
    }
}
