package com.tasktrack.TaskTrack.models;

import jakarta.persistence.*;

@Entity
@Table(name = "projects_table")
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "projectsTableSeq", sequenceName = "projectsTableSeq", allocationSize = 1)
    private Long projectId;

    private String projectName;

    private String projectDescription;

    public ProjectsEntity() {
    }

    public ProjectsEntity(String projectName, String projectDescription) {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}


