package com.prosupport.lolchampions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.prosupport.lolchampions.async.DataLoaderAsyncTask;
import com.prosupport.lolchampions.data.Champion;
import com.prosupport.lolchampions.listeners.OnChampionsReadyListener;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements OnChampionsReadyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Sync
//        List<Champion> championList = ChampionsManager.getInstance(this).getChampions();

//      Async
        new DataLoaderAsyncTask(this).execute(this);
    }

    @Override
    public void onReady(List<Champion> champions) {
        Log.d("test--","champions:" + champions.size());
    }
}
