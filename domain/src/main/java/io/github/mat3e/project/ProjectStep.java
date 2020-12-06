package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class ProjectStep {
    private int id;
    private String description;
    private int daysToProjectDeadline;
    private Project project;


    ProjectStep(@NonNull String description, int daysToProjectDeadline, Project project) {
        this.description = description;
        this.daysToProjectDeadline = daysToProjectDeadline;
        this.project = project;
    }

    ProjectStep(final int id, @NonNull final String description, final int daysToProjectDeadline, final Project project) {
        this.id = id;
        this.description = description;
        this.daysToProjectDeadline = daysToProjectDeadline;
        this.project = project;
    }
}
