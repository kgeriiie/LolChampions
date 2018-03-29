package com.prosupport.lolchampions.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.prosupport.lolchampions.App;
import com.prosupport.lolchampions.R;

import java.util.ArrayList;
import java.util.List;

public class Variable implements Parcelable {
    public String key;
    public String link;
    private List<Double> coeff = new ArrayList<>();

    public Double getFirstCoEff() {
        if (coeff.isEmpty()) return 0.0;

        return coeff.get(0);
    }

    public String translateLinkVal() {
        switch (link) {
            case "bonusattackdamage":
                return App.getStringRes(R.string.bonus_attack_text);
            case "spelldamage":
                return App.getStringRes(R.string.ability_power_text);

                default:
                    return App.getStringRes(R.string.empty_text);
        }
    }

    /**
     *  Parcelables
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
        dest.writeString(this.link);
        dest.writeList(this.coeff);
    }

    public Variable() {
    }

    protected Variable(Parcel in) {
        this.key = in.readString();
        this.link = in.readString();
        this.coeff = new ArrayList<Double>();
        in.readList(this.coeff, Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Variable> CREATOR = new Parcelable.Creator<Variable>() {
        @Override
        public Variable createFromParcel(Parcel source) {
            return new Variable(source);
        }

        @Override
        public Variable[] newArray(int size) {
            return new Variable[size];
        }
    };
}
