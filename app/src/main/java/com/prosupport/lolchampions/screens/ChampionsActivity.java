package com.prosupport.lolchampions.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.adapter.ChampionsAdapter;
import com.prosupport.lolchampions.async.ChampionsAsyncTask;
import com.prosupport.lolchampions.data.Champion;
import com.prosupport.lolchampions.listeners.OnChampionsReadyListener;
import com.prosupport.lolchampions.listeners.OnItemClickListener;

import java.util.List;

public class ChampionsActivity extends AppCompatActivity implements OnChampionsReadyListener, OnItemClickListener<Champion> {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ChampionsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ChampionsAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        new ChampionsAsyncTask(this).execute(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.search, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                mAdapter.searchFor(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public void onReady(List<Champion> champions) {
        mAdapter.updateChampions(champions, this);
    }

    @Override
    public void onItemClick(int position, Champion item) {
        Log.d("ChampionsActivity", item.title);
        Intent detailIntent = new Intent(this, ChampionDetailActivity.class);
        detailIntent.putExtra(ChampionDetailActivity.CHAMPION_EXTRA, item);
        startActivity(detailIntent);
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }
}
