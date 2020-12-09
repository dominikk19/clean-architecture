package io.github.mat3e.project;

import io.github.mat3e.project.dto.ProjectDto;
import io.github.mat3e.project.dto.ProjectStepDto;
import io.github.mat3e.task.TaskFacade;
import io.github.mat3e.task.dto.TaskDto;
import io.github.mat3e.task.vo.TaskEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectFacade {
    private final ProjectRepository projectRepository;
    private final TaskFacade taskFacade;
    private final ProjectFactory projectFactory;

    public void handle(TaskEvent taskEvent) {
        var stepId = Integer.parseInt(taskEvent.getSourceId().getId());
        switch (taskEvent.getState()) {
            case UPDATE:
            case UNDONE:
                updateStep(stepId, true);
                break;
            case DELETED:
                updateStep(stepId, false);
                break;
            case DONE:
            default:
                break;
        }
    }

    void updateStep(int stepId, boolean done) {
        projectRepository.findByNestedStepId(stepId)
                .ifPresent(project -> {
                    project.updateStep(stepId, done);
                    projectRepository.save(project);
                });
    }


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

        return projectRepository.findById(projectId)
                .map(project -> {
                    var tasks = project.convertToTasks(projectDeadline);
                    projectRepository.save(project);
                    return taskFacade.createTasks(tasks);
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
