package io.github.mat3e.project;

import io.github.mat3e.project.dto.ProjectDto;

import java.util.Optional;
import java.util.Set;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
public interface ProjectQueryRepository {

    Optional<ProjectDto> findDtoById(int id);

    <T> Set<T> findBy(Class<T> type);

    int count();
}
