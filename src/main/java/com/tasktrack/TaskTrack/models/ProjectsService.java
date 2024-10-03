package com.tasktrack.TaskTrack.models;

import com.tasktrack.TaskTrack.repositorys.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {

        @Autowired
        public ProjectsRepository projectsRepository;

        public ProjectsEntity saveProject(ProjectsEntity projectsEntity) {
            return projectsRepository.save(projectsEntity);
        }

        public List<ProjectsEntity> getAllProjects() {
            return projectsRepository.findAll();
        }

        public Optional<ProjectsEntity> getProjectById(Long projectId) {
            return projectsRepository.findById(projectId);
        }

        public void deleteProject(Long projectId) {
            projectsRepository.deleteById(projectId);
        }

}