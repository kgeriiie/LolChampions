package com.prosupport.lolchampions.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Champion implements Parcelable {
    public int id;
    public String name;
    @SerializedName("lore")
    public String description;
    public String title;
    public Image image;
    public Info info;
    public Skill passive;

    public List<Skin> skins = new ArrayList<>();
    @SerializedName("spells")
    public List<Skill> skills = new ArrayList<>();

    public String getDefaultImage() {
        if (image != null) {
            return image.getImageUrl();
        }

        return null;
    }

    public String getDefaultSkinImage() {
        if (!skins.isEmpty()) {
            return skins.get(0).getSkinImageUrl(name);
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
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.title);
        dest.writeParcelable(this.image, flags);
        dest.writeParcelable(this.info, flags);
        dest.writeParcelable(this.passive, flags);
        dest.writeTypedList(this.skins);
        dest.writeTypedList(this.skills);
    }

    public Champion() {
    }

    protected Champion(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.title = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.info = in.readParcelable(Info.class.getClassLoader());
        this.passive = in.readParcelable(Skill.class.getClassLoader());
        this.skins = in.createTypedArrayList(Skin.CREATOR);
        this.skills = in.createTypedArrayList(Skill.CREATOR);
    }

    public static final Parcelable.Creator<Champion> CREATOR = new Parcelable.Creator<Champion>() {
        @Override
        public Champion createFromParcel(Parcel source) {
            return new Champion(source);
        }

        @Override
        public Champion[] newArray(int size) {
            return new Champion[size];
        }
    };
}
