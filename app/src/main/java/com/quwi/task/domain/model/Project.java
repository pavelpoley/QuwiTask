package com.quwi.task.domain.model;

import java.io.Serializable;

public class Project implements Serializable {

    private final int id;
    private final String name;
    private final String logUrl;

    public Project(int id, String name, String logUrl) {
        this.id = id;
        this.name = name;
        this.logUrl = logUrl;
    }

    public String getName() {
        return name;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public int getId() {
        return id;
    }
}
