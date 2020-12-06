package io.github.mat3e.task;

import org.springframework.data.repository.Repository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlTaskRepository extends TaskRepository, Repository<Task, Integer> {
}
