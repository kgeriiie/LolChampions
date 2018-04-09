package com.prosupport.lolchampions.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

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
    public Stat stats;

    public List<String> tags = new ArrayList<>();
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

    public String getTags() {
        return "(" + TextUtils.join(", ", tags) + ")";
    }

    protected Champion(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        title = in.readString();
        image = in.readParcelable(Image.class.getClassLoader());
        info = in.readParcelable(Info.class.getClassLoader());
        passive = in.readParcelable(Skill.class.getClassLoader());
        tags = in.createStringArrayList();
        skins = in.createTypedArrayList(Skin.CREATOR);
        skills = in.createTypedArrayList(Skill.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(title);
        dest.writeParcelable(image, flags);
        dest.writeParcelable(info, flags);
        dest.writeParcelable(passive, flags);
        dest.writeStringList(tags);
        dest.writeTypedList(skins);
        dest.writeTypedList(skills);
    }

    /**
     *  Parcelables
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Champion> CREATOR = new Creator<Champion>() {
        @Override
        public Champion createFromParcel(Parcel in) {
            return new Champion(in);
        }

        @Override
        public Champion[] newArray(int size) {
            return new Champion[size];
        }
    };
}
