package com.prosupport.lolchampions.customView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.prosupport.lolchampions.R;
import com.prosupport.lolchampions.data.Champion;

public class HeroStatView extends ConstraintLayout {

    public HeroStatView(Context context) {
        super(context);
        commonInit(context);
    }

    public HeroStatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonInit(context);
    }

    public HeroStatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonInit(context);
    }

    private void commonInit(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.view_hero_stat, this, true);
        }
    }

    public void update(Champion champion) {
        TextView lifeTxt = findViewById(R.id.lifeTxt);
        TextView damageTxt = findViewById(R.id.damageTxt);
        TextView armorTxt = findViewById(R.id.armorTxt);

        double hp = 0;
        double damage = 0;
        double armor = 0;

        String hpPerLevel = "";
        String damagePerLevel = "";
        String armorPerLevel = "";

        if (champion != null && champion.stats != null) {
            hp = champion.stats.hp;
            damage = champion.stats.attackdamage;
            armor = champion.stats.armor;

//            hpPerLevel = " (+"+ String.valueOf(champion.stats.hpperlevel) + "per szint)";
//            damagePerLevel = " (+"+ String.valueOf(champion.stats.attackdamageperlevel) + "per szint)";
//            armorPerLevel = " (+"+ String.valueOf(champion.stats.armorperlevel) + "/ szint)";
        }

        lifeTxt.setText(String.valueOf(hp) + hpPerLevel);
        damageTxt.setText(String.valueOf(damage) + damagePerLevel);
        armorTxt.setText(String.valueOf(armor) + armorPerLevel);
    }
}
