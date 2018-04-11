package com.prosupport.lolchampions.customView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.data.Skill;

public class HeroSkillView extends ConstraintLayout {

    public HeroSkillView(Context context) {
        super(context);
        commonInit(context);
    }

    public HeroSkillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonInit(context);
    }

    public HeroSkillView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonInit(context);
    }

    private void commonInit(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.view_skill, this, true);
        }
    }

    public void update(Skill skill) {
        if (skill == null) {
            return;
        }

        ImageView skillImageView = findViewById(R.id.skillImageView);
        skillImageView.setClipToOutline(true);
        TextView nameTxt = findViewById(R.id.nameTxt);
        TextView costTxt = findViewById(R.id.costTxt);
        TextView rangeTxt = findViewById(R.id.rangeTxt);

        Glide.with(this).load(skill.image.getImageUrl()).into(skillImageView);
        nameTxt.setText(skill.name);
        costTxt.setText(skill.getCost());
        rangeTxt.setText(skill.getRange());
    }
}
