package com.prosupport.lolchampions.listeners;

import com.prosupport.lolchampions.data.Champion;

import java.util.List;

public interface OnChampionsReadyListener {
    void onReady(List<Champion> champions);
}
