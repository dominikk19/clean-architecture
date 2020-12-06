package io.github.mat3e.task;

import io.github.mat3e.project.dto.SimpleProject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Task {
    private int id;
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private int changesCount;
    private String additionalComment;
    private SimpleProject project;

    Task(String description, ZonedDateTime deadline, SimpleProject project) {
        this.description = description;
        this.deadline = deadline;
        this.project = project;
    }
}
