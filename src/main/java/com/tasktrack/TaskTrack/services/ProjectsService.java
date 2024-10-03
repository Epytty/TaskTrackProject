package com.tasktrack.TaskTrack.services;

import com.tasktrack.TaskTrack.entities.Project;
import com.tasktrack.TaskTrack.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProjectsService {

        @Autowired
        public ProjectsRepository projectsRepository;

        public Project saveProject(Project project) {
            return projectsRepository.save(project);
        }

        public List<Project> getAllProjects() {
            return projectsRepository.findAll();
        }

        public Optional<Project> getProjectById(Long id) {
            return projectsRepository.findById(id);
        }

        public void deleteProject(Long id) {
            projectsRepository.deleteById(id);
        }

}