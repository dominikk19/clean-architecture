package io.github.mat3e.project;

import io.github.mat3e.project.dto.ProjectDto;
import io.github.mat3e.project.dto.ProjectStepDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectFactory {
    public Project fromDto(ProjectDto toSave) {
        return Project.restore(
                new ProjectSnapshot(toSave.getId(),
                        toSave.getName(),
                        toSave.getSteps()
                                .stream()
                                .map(this::convertFrom)
                                .collect(Collectors.toSet())));
    }

    private ProjectStepSnapshot convertFrom(ProjectStepDto projectStepDto) {
        return new ProjectStepSnapshot(projectStepDto.getId(),
                projectStepDto.getDescription(),
                projectStepDto.getDaysToProjectDeadline(),
                false,
                false
        );
    }
}
