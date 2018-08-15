package com.prosupport.lolchampions.data;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.prosupport.lolchampions.Constants;

public class Image implements Parcelable {
    private String group;
    @SerializedName("full")
    private String key;

    public String getImageUrl() {
        if (group != null && key != null) {
            return String.format(Constants.IMG_BASE_URL, group, key);
        }

        return null;
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
        dest.writeString(this.group);
        dest.writeString(this.key);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.group = in.readString();
        this.key = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
