package com.prosupport.lolchampions.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Info implements Parcelable {
    public int difficult;
    public int attack;
    public int defense;
    public int magic;

    /**
     *  Parcelables
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.difficult);
        dest.writeInt(this.attack);
        dest.writeInt(this.defense);
        dest.writeInt(this.magic);
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.difficult = in.readInt();
        this.attack = in.readInt();
        this.defense = in.readInt();
        this.magic = in.readInt();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
