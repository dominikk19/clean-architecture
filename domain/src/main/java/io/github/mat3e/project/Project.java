package io.github.mat3e.project;

import io.github.mat3e.project.dto.SimpleProject;
import io.github.mat3e.project.dto.SimpleProjectSnapshot;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    SimpleProject toSimpleProject() {
        return SimpleProject.restore(new SimpleProjectSnapshot(id, name));
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
                // collecting first to allow multiple id=0
                .forEach(this::addStep);
        // can be converted to internal domain event, e.g. removed ids
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
            return new Step(snapshot.getId(), snapshot.getDescription(), snapshot.getDaysToProjectDeadline());
        }

        private int id;
        private String description;
        private int daysToProjectDeadline;

        Step(final int id, final String description, final int daysToProjectDeadline) {
            this.id = id;
            this.description = description;
            this.daysToProjectDeadline = daysToProjectDeadline;
        }

        ProjectStepSnapshot getSnapshot() {
            return new ProjectStepSnapshot(id, description, daysToProjectDeadline);
        }
    }

}
