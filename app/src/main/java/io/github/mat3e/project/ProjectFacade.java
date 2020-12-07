package io.github.mat3e.project;

import io.github.mat3e.project.dto.ProjectDto;
import io.github.mat3e.project.dto.ProjectStepDto;
import io.github.mat3e.project.dto.SimpleProject;
import io.github.mat3e.task.TaskFacade;
import io.github.mat3e.task.TaskQueryRepository;
import io.github.mat3e.task.dto.TaskDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectFacade {
    private final ProjectRepository projectRepository;
    private final TaskFacade taskFacade;
    private final ProjectFactory projectFactory;
    private final TaskQueryRepository taskQueryRepository;

    ProjectDto save(ProjectDto toSave) {
        if (toSave.getId() != 0) {
            return convertToDto(saveWithId(toSave));
        }
        if (toSave.getSteps().stream().anyMatch(step -> step.getId() != 0)) {
            throw new IllegalStateException("Cannot add project with existing steps");
        }
        return convertToDto(projectRepository.save(projectFactory.fromDto(toSave)));
    }

    private Project saveWithId(ProjectDto dtoToSave) {
        return projectRepository.findById(dtoToSave.getId())
                .map(existingProject -> {
                    var toSave = projectFactory.fromDto(dtoToSave);
                    var removedSteps = existingProject.modifySteps(toSave.getSnapshot().getSteps());
                    projectRepository.save(existingProject);
                    removedSteps.forEach(projectRepository::delete);
                    return existingProject;
                }).orElseGet(() -> projectRepository.save(projectFactory.fromDto(dtoToSave)));
    }

    List<TaskDto> createTasks(int projectId, ZonedDateTime projectDeadline) {
        if (taskQueryRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            throw new IllegalStateException("There are still some undone tasks from a previous project instance!");
        }
        return projectRepository.findById(projectId).map(project -> {
            List<TaskDto> tasks = project.getSnapshot().getSteps().stream()
                    .map(step -> TaskDto.builder()
                            .withDescription(step.getDescription())
                            .withDeadline(projectDeadline.plusDays(step.getDaysToProjectDeadline()))
                            .build()
                    ).collect(toList());
            return taskFacade.saveAll(tasks, SimpleProject.restore(project.toSimpleProject().getSnapshot()));
        }).orElseThrow(() -> new IllegalArgumentException("No project found with id: " + projectId));
    }

    private ProjectDto convertToDto(Project project) {
        return ProjectDto.create(project.getSnapshot().getId(), project.getSnapshot().getName(), project.getSnapshot().getSteps().stream()
                .map(this::convertToDto)
                .collect(toSet()));
    }


    private ProjectStepDto convertToDto(ProjectStepSnapshot projectStep) {
        return ProjectStepDto.create(projectStep.getId(), projectStep.getDescription(), projectStep.getDaysToProjectDeadline());
    }
}
