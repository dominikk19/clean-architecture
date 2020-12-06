package io.github.mat3e.task;

import io.github.mat3e.task.dto.TaskDto;
import io.github.mat3e.task.dto.TaskWithChangesDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
public interface TaskQueryRepository {
    List<TaskDto> findAllBy();

    List<TaskWithChangesDto> findAllWithChangesBy();

    Optional<TaskDto> getDtoById(int id);

    boolean existsByDoneIsFalseAndProject_Id(int id);

    <T> Set<T> findBy(Class<T> type);

    int count();
}
