package com.quwi.task.domain;

import com.quwi.task.data.network.model.response.ProjectsItem;
import com.quwi.task.domain.model.Project;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<Project> toDomain(List<ProjectsItem> projectsItemList) {
        List<Project> result = new ArrayList<>();
        for (ProjectsItem projectsItem : projectsItemList) {
            //filter active projects
            if (projectsItem.getIsActive() == 1) {
                Project project = new Project(
                        projectsItem.getId(),
                        projectsItem.getName(),
                        projectsItem.getLogoUrl()
                );
                result.add(project);
            }
        }
        return result;
    }
}
