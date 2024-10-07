package com.tasktrack.TaskTrack.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectsEntity project;

}

