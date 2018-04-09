package com.prosupport.lolchampions.data;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skill implements Parcelable {
    public String name;
    public String cooldownBurn;
    public String resource;
    public String costType;
    public String description;
    private String sanitizedTooltip;

    public int maxrank;
    private String costBurn;
    private String rangeBurn;
    public Image image;

    private List<String> effectBurn = new ArrayList<>();
    private List<Variable> vars = new ArrayList<>();

    private Map<String, String> skillValueReplacementMap = new HashMap<>();

    public String getSanitizedTooltip() {
        if (sanitizedTooltip != null) {
            buildMap();

            for (String key : skillValueReplacementMap.keySet()) {
                sanitizedTooltip = sanitizedTooltip.replace(key, skillValueReplacementMap.get(key));
            }

            return sanitizedTooltip;
        }

        return null;
    }

    public String getRange() {
        return rangeBurn;
    }

    public String getCost() {
        if (resource != null) {
            buildMap();

            for (String key : skillValueReplacementMap.keySet()) {
                resource = resource.replace(key, skillValueReplacementMap.get(key));
            }
        }

        return resource;
    }

    @SuppressLint("DefaultLocale")
    private void buildMap() {
        if (skillValueReplacementMap.isEmpty()) {
            String effKeyMask = "{{ e%d }}";
            String varKeyMask = "{{ %s }}";

            for (int effIndex = 0; effIndex < effectBurn.size(); effIndex++) {
                skillValueReplacementMap.put(String.format(effKeyMask, effIndex), effectBurn.get(effIndex));
            }

            for (int varIndex = 0; varIndex < vars.size(); varIndex++) {
                Variable variable = vars.get(varIndex);
                skillValueReplacementMap.put(String.format(varKeyMask, variable.key), String.format("%d%s", (int) (variable.getFirstCoEff() * 100), variable.translateLinkVal()));
            }

            skillValueReplacementMap.put("{{ cost }}", costBurn);
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
        dest.writeString(this.name);
        dest.writeString(this.cooldownBurn);
        dest.writeString(this.resource);
        dest.writeString(this.costType);
        dest.writeString(this.description);
        dest.writeString(this.sanitizedTooltip);
        dest.writeInt(this.maxrank);
        dest.writeString(this.costBurn);
        dest.writeString(this.rangeBurn);
        dest.writeParcelable(this.image, flags);
        dest.writeStringList(this.effectBurn);
        dest.writeTypedList(this.vars);
        dest.writeInt(this.skillValueReplacementMap.size());
        for (Map.Entry<String, String> entry : this.skillValueReplacementMap.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public Skill() {
    }

    protected Skill(Parcel in) {
        this.name = in.readString();
        this.cooldownBurn = in.readString();
        this.resource = in.readString();
        this.costType = in.readString();
        this.description = in.readString();
        this.sanitizedTooltip = in.readString();
        this.maxrank = in.readInt();
        this.costBurn = in.readString();
        this.rangeBurn = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.effectBurn = in.createStringArrayList();
        this.vars = in.createTypedArrayList(Variable.CREATOR);
        int skillValueReplacementMapSize = in.readInt();
        this.skillValueReplacementMap = new HashMap<String, String>(skillValueReplacementMapSize);
        for (int i = 0; i < skillValueReplacementMapSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.skillValueReplacementMap.put(key, value);
        }
    }

    public static final Parcelable.Creator<Skill> CREATOR = new Parcelable.Creator<Skill>() {
        @Override
        public Skill createFromParcel(Parcel source) {
            return new Skill(source);
        }

        @Override
        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };
}
