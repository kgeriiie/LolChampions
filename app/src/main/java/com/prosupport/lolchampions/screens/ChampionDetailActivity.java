package com.prosupport.lolchampions.screens;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.data.Champion;

public class ChampionDetailActivity extends AppCompatActivity {

    public static final String CHAMPION_EXTRA = "CHAMPION_EXTRA";

    private Champion mChampion;
    private ObjectAnimator mAnimator;
    private float mLastAlpha;

    private ImageView headerImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);

        // TODO: Elkérni az extrában átadott hőst.

        setupToolbar();

        bindViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    private void bindViews() {
        headerImageView = findViewById(R.id.headerImageView);

        // TODO: Kivezetni a többi view-t az XML-ből.
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("");

        AppBarLayout appBarLayout = findViewById(R.id.appbarLayout);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                float offsetAlpha = (appBarLayout.getY() / appBarLayout.getTotalScrollRange());
                float alpha = 1 - (offsetAlpha * -1);
                if (alpha < 0.5) {
                    alpha = 0;
                }
                if (mLastAlpha == alpha) {
                    return;
                }
                mLastAlpha = alpha;

                if (mAnimator != null && mAnimator.isRunning()) {
                    mAnimator.cancel();
                }

//                mAnimator = ObjectAnimator.ofFloat(heroCardView, "alpha", alpha);
//                mAnimator.setDuration(300);
//                mAnimator.start();
//                heroCardView.setAlpha( 1 - (offsetAlpha * -1));
            }
        });
    }
}
