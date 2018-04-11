package com.prosupport.lolchampions.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Stat implements Parcelable {
    public double armor;
    public double armorperlevel;
    public double attackdamage;
    public double attackdamageperlevel;
    public double hp;
    public double hpperlevel;

    protected Stat(Parcel in) {
        armor = in.readDouble();
        armorperlevel = in.readDouble();
        attackdamage = in.readDouble();
        attackdamageperlevel = in.readDouble();
        hp = in.readDouble();
        hpperlevel = in.readDouble();
    }

    public static final Creator<Stat> CREATOR = new Creator<Stat>() {
        @Override
        public Stat createFromParcel(Parcel in) {
            return new Stat(in);
        }

        @Override
        public Stat[] newArray(int size) {
            return new Stat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(armor);
        dest.writeDouble(armorperlevel);
        dest.writeDouble(attackdamage);
        dest.writeDouble(attackdamageperlevel);
        dest.writeDouble(hp);
        dest.writeDouble(hpperlevel);
    }
}
