package com.prosupport.lolchampions.data;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.prosupport.lolchampions.Constants;

public class Skin implements Parcelable {
    private int id;
    private int num;
    private String name;

    @SuppressLint("DefaultLocale")
    public String getSkinImageUrl(String championName) {
        if (championName == null) return null;

        return String.format(Constants.IMG_SKIN_URL, championName, num);
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
        dest.writeInt(this.id);
        dest.writeInt(this.num);
        dest.writeString(this.name);
    }

    public Skin() {
    }

    protected Skin(Parcel in) {
        this.id = in.readInt();
        this.num = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Skin> CREATOR = new Parcelable.Creator<Skin>() {
        @Override
        public Skin createFromParcel(Parcel source) {
            return new Skin(source);
        }

        @Override
        public Skin[] newArray(int size) {
            return new Skin[size];
        }
    };
}
