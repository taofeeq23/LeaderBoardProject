package com.gads.project.leaderboard;

import com.google.gson.annotations.SerializedName;

public class SkillIQ {

    private String name;

    private String score;

    private String country;

    @SerializedName("badgeUrl")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}