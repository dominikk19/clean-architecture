package io.github.mat3e.project.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@JsonDeserialize(as = ProjectStepDto.DeserializationImpl.class)
public interface ProjectStepDto {

    static ProjectStepDto create(final int id, final String description, final int daysToProjectDeadline) {
        return new DeserializationImpl(id, description, daysToProjectDeadline);
    }

    int getId();

    String getDescription();

    int getDaysToProjectDeadline();

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    class DeserializationImpl implements ProjectStepDto {
        private final int id;
        private final String description;
        private final int daysToProjectDeadline;

        @Override
        public int getId() {
            return id;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getDaysToProjectDeadline() {
            return daysToProjectDeadline;
        }
    }
}
