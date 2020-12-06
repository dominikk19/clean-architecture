package io.github.mat3e.task;

import io.github.mat3e.task.dto.TaskDto;

import java.util.Optional;
import java.util.Set;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
public interface TaskQueryRepository {

    Optional<TaskDto> getDtoById(int id);

    <T> Set<T> findBy(Class<T> type);

    boolean existsByDoneIsFalseAndProject_Id(int id);

    int count();
}
