package io.github.mat3e.project;

import io.github.mat3e.task.vo.TaskCreator;
import io.github.mat3e.task.vo.TaskSourceId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class Project {

    static Project restore(ProjectSnapshot snapshot) {
        return new Project(snapshot.getId(), snapshot.getName(), snapshot.getSteps());
    }

    private int id;
    private String name;
    private final Set<Step> steps = new HashSet<>();


    public Project(int id, String name, final Set<ProjectStepSnapshot> steps) {
        this.id = id;
        this.name = name;
        modifySteps(steps);
    }

    ProjectSnapshot getSnapshot() {
        return new ProjectSnapshot(id, name, steps.stream().map(Step::getSnapshot).collect(toSet()));
    }

    Set<TaskCreator> convertToTasks(final ZonedDateTime deadline) {
        if (steps.stream().anyMatch(step -> step.hasCorrespondingTask && step.correspondingTaskIsDone)) {
            throw new IllegalStateException("There are still some undone tasks from a previous project instance!");
        }
        var result = steps.stream()
                .map(step -> new TaskCreator(
                        new TaskSourceId(String.valueOf(step.id)),
                        step.description,
                        deadline.plusDays(step.daysToProjectDeadline)))
                .collect(toSet());
        //todo add event to set to value true
        steps.forEach(step -> {
            step.hasCorrespondingTask = true;
            step.correspondingTaskIsDone = false;
        });
        return result;
    }

    void updateStep(final int stepId, final boolean taskDone) {
        steps.stream()
                .filter(step -> step.id == stepId)
                .forEach(step -> step.correspondingTaskIsDone = taskDone);
    }

    Set<Step> modifySteps(final Set<ProjectStepSnapshot> stepSnapshots) {
        Set<Step> stepsToRemove = new HashSet<>();
        steps.forEach(existingStep -> stepSnapshots.stream()
                .filter(potentialOverride -> existingStep.id == potentialOverride.getId())
                .findFirst()
                .ifPresentOrElse(
                        overridingStep -> {
                            existingStep.description = overridingStep.getDescription();
                            existingStep.daysToProjectDeadline = overridingStep.getDaysToProjectDeadline();
                        },
                        () -> stepsToRemove.add(existingStep)
                )
        );
        stepsToRemove.forEach(this::removeStep);
        stepSnapshots.stream()
                .filter(newStep -> steps.stream()
                        .noneMatch(existingStep -> existingStep.id == newStep.getId())
                ).map(Step::restore)
                .collect(toSet())
                .forEach(this::addStep);
        return stepsToRemove;
    }

    private void addStep(Step step) {
        steps.add(step);
    }

    private void removeStep(Step step) {
        steps.remove(step);
    }


    static class Step {

        static Step restore(ProjectStepSnapshot snapshot) {
            return new Step(snapshot.getId(),
                    snapshot.getDescription(),
                    snapshot.getDaysToProjectDeadline(),
                    snapshot.isHasCorrespondingTask(),
                    snapshot.isHasCorrespondingTask());
        }

        private int id;
        private String description;
        private int daysToProjectDeadline;
        private boolean hasCorrespondingTask;
        private boolean correspondingTaskIsDone;

        Step(final int id, final String description,
             final int daysToProjectDeadline,
             final boolean hasCorrespondingTask,
             final boolean correspondingTaskIsDone) {
            this.id = id;
            this.description = description;
            this.daysToProjectDeadline = daysToProjectDeadline;
            this.hasCorrespondingTask = hasCorrespondingTask;
            this.correspondingTaskIsDone = correspondingTaskIsDone;

        }

        ProjectStepSnapshot getSnapshot() {
            return new ProjectStepSnapshot(id, description, daysToProjectDeadline, hasCorrespondingTask, correspondingTaskIsDone);
        }
    }

}
