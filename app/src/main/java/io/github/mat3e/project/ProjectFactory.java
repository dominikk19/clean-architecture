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
        var project = new Project(toSave.getId(), toSave.getName());
        toSave.getSteps().stream().map(step -> convertFrom(step, project)).collect(Collectors.toSet())
                .forEach(project::addStep);
        return project;
    }

    private ProjectStep convertFrom(ProjectStepDto projectStepDto, Project project) {
        return new ProjectStep(projectStepDto.getId(), projectStepDto.getDescription(), projectStepDto.getDaysToProjectDeadline(), project);
    }
}
