package io.github.mat3e.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@JsonDeserialize(as = ProjectDto.DeserializationImpl.class)
public interface ProjectDto {

    static ProjectDto create(final int id, final String name, final Set<ProjectStepDto> steps) {
        return new DeserializationImpl(id, name, steps);
    }

    int getId();

    String getName();

    Set<ProjectStepDto> getSteps();

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    class DeserializationImpl implements ProjectDto {
        private final int id;
        private final String name;
        private final Set<ProjectStepDto> steps;


        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Set<ProjectStepDto> getSteps() {
            return steps;
        }
    }
}
