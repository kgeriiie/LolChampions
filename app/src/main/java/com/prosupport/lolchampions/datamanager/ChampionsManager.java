package com.prosupport.lolchampions.datamanager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prosupport.lolchampions.data.Champion;
import com.prosupport.lolchampions.data.ChampionsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChampionsManager {

    private List<Champion> champions = new ArrayList<>();
    private Context context;

    private static class InstanceHolder {
        static ChampionsManager sInstance = new ChampionsManager();
    }

    private ChampionsManager() {}

    public static ChampionsManager getInstance(Context context) {
        InstanceHolder.sInstance.context = context;
        return InstanceHolder.sInstance;
    }

    public List<Champion> getChampions() {
        if (champions.isEmpty()) {
            champions = loadChampions();
        }

        return champions;
    }

    private List<Champion> loadChampions() {
        Gson gson = new Gson();
        Type responseType = new TypeToken<ChampionsResponse>(){}.getType();
        ChampionsResponse response = gson.fromJson(loadChampionsJSONFromAsset(), responseType);

        return new ArrayList<>(response.data.values());
    }

    private String loadChampionsJSONFromAsset() {
        String result = null;

        try {
            InputStream is = context.getAssets().open("champions.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            result = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
