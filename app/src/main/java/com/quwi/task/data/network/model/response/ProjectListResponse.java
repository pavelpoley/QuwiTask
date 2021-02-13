package com.quwi.task.data.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectListResponse {

    @SerializedName("projects")
    private List<ProjectsItem> projects;

    public List<ProjectsItem> getProjects() {
        return projects;
    }
}

