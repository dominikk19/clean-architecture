package io.github.mat3e.task;

import io.github.mat3e.project.dto.SimpleProject;
import io.github.mat3e.task.dto.TaskDto;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
class TaskFactory {
    Task from(final TaskDto source, final SimpleProject project) {
        return Task.restore(

                new TaskSnapshot(source.getId(),
                        source.getDescription(),
                        source.isDone(),
                        source.getDeadline(),
                        0,
                        source.getAdditionalComment(),
                        project.getSnapshot())

        );
    }
}
