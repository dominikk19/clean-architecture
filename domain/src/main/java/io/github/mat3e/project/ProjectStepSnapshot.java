package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
class ProjectStepSnapshot {
    private int id;
    private String description;
    private int daysToProjectDeadline;
    private ProjectSnapshot project;

    ProjectStepSnapshot(int id, String description, int daysToProjectDeadline) {
        this.id = id;
        this.description = description;
        this.daysToProjectDeadline = daysToProjectDeadline;
    }
}
