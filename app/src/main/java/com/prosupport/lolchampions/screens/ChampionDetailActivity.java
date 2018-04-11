package com.prosupport.lolchampions.screens;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.customView.HeroSkillView;
import com.prosupport.lolchampions.customView.HeroStatView;
import com.prosupport.lolchampions.data.Champion;

public class ChampionDetailActivity extends AppCompatActivity {

    public static final String CHAMPION_EXTRA = "CHAMPION_EXTRA";

    private Champion mChampion;
    private ObjectAnimator mAnimator;
    private float mLastAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AppBarLayout appBarLayout = findViewById(R.id.appbarLayout);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitleEnabled(false);

        setTitle("");

        ImageView headerImageView = findViewById(R.id.headerImageView);

        final CardView heroCardView = findViewById(R.id.heroCardView);
        final ImageView heroImageView = findViewById(R.id.heroImageView);
        TextView nameTextView = findViewById(R.id.nameTxt);
        TextView titleTextView = findViewById(R.id.titleTxt);
        TextView tagTextView = findViewById(R.id.tagTxt);
        TextView descriptionTextView = findViewById(R.id.descriptionTxt);
        HeroStatView statView = findViewById(R.id.heroStatLayout);

        mChampion = getIntent().getParcelableExtra(CHAMPION_EXTRA);
        Glide.with(this).load(mChampion.getDefaultImage()).into(heroImageView);

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.placeholder);
        options.error(R.drawable.placeholder);
        Glide.with(this).load(mChampion.getDefaultSkinImage()).apply(options).into(headerImageView);

        nameTextView.setText(mChampion.name);
        titleTextView.setText(mChampion.title);
        tagTextView.setText(mChampion.getTags());
        descriptionTextView.setText(mChampion.description);

        statView.update(mChampion);

        updateSkills();


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

                mAnimator = ObjectAnimator.ofFloat(heroCardView, "alpha", alpha);
                mAnimator.setDuration(300);
                mAnimator.start();
//                heroCardView.setAlpha( 1 - (offsetAlpha * -1));
            }
        });
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

    private void updateSkills() {
        if (mChampion == null || mChampion.skills == null || mChampion.skills.size() != 4) {
            return;
        }

        HeroSkillView firstSkillView = findViewById(R.id.firstSkill);
        HeroSkillView secondSkillView = findViewById(R.id.secondSkill);
        HeroSkillView thirdSkillView = findViewById(R.id.thirdSkill);
        HeroSkillView fourthSkillView = findViewById(R.id.fourthSkill);
        HeroSkillView fifthSkillView = findViewById(R.id.fifthSkill);

        firstSkillView.update(mChampion.passive);
        secondSkillView.update(mChampion.skills.get(0));
        thirdSkillView.update(mChampion.skills.get(1));
        fourthSkillView.update(mChampion.skills.get(2));
        fifthSkillView.update(mChampion.skills.get(3));

    }
}
