package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
class ProjectSnapshot {
    private int id;
    private String name;
    private final Set<ProjectStepSnapshot> steps = new HashSet<>();

    ProjectSnapshot(final int id, final String name, final Set<ProjectStepSnapshot> steps) {
        this.id = id;
        this.name = name;
        this.steps.addAll(steps);
    }
}
