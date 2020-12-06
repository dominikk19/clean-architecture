package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@Entity
@Table(name = "project_steps")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class SqlProjectStep {
    static SqlProjectStep fromStep(ProjectStep source, SqlProject project) {
        return new SqlProjectStep(source.getId(), source.getDescription(), source.getDaysToProjectDeadline(), project);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @NotNull
    private String description;
    private int daysToProjectDeadline;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private SqlProject project;

    private SqlProjectStep(int id, @NotNull String description, int daysToProjectDeadline, SqlProject project) {
        this.id = id;
        this.description = description;
        this.daysToProjectDeadline = daysToProjectDeadline;
        this.project = project;
    }

    ProjectStep toStep(Project project) {
        return new ProjectStep(id, description, daysToProjectDeadline, project);
    }
}

