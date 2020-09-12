package com.gads.project.leaderboard;

import com.google.gson.annotations.SerializedName;

public class SubmitModel {

    @SerializedName("Email Address")
    private String email;

    @SerializedName("Name")
    private String name;

    @SerializedName("Last Name")
    private String lastName;

    @SerializedName("Link to project")
    private String projectLink;


    public SubmitModel(String email, String name, String lastName, String projectLink) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.projectLink = projectLink;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProjectLink() {
        return projectLink;
    }
}