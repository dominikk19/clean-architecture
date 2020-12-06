package io.github.mat3e.project;

import io.github.mat3e.project.dto.ProjectDto;

import java.util.List;
import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
public interface ProjectQueryRepository {
    int count();

    List<ProjectDto> findBy();

    Optional<ProjectDto> findDtoById(int id);
}
