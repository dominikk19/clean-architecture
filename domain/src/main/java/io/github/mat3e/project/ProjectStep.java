package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "project_steps")
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class ProjectStep {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @NotNull
    private String description;
    private int daysToProjectDeadline;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @PersistenceConstructor
    protected ProjectStep() {
    }

    ProjectStep(@NotNull String description, int daysToProjectDeadline, Project project) {
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

    void setProject(Project project) {
        this.project = project;
    }

}
