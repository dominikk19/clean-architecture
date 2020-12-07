package io.github.mat3e.task;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TaskInitializer {
    private final TaskRepository taskRepository;
    private final TaskQueryRepository taskQueryRepository;

    void init() {
        if (taskQueryRepository.count() == 0) {
            taskRepository.save(Task.restore(
                    new TaskSnapshot(0,
                            "Example task",
                            false,
                            ZonedDateTime.now(),
                            0,
                            null,
                            null
                    )));
        }
    }
}
