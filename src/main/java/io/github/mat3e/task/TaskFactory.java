package io.github.mat3e.task;

import io.github.mat3e.project.query.SimpleProjectQueryDto;
import org.springframework.stereotype.Service;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@Service
class TaskFactory {
    Task from(final TaskDto source, final SimpleProjectQueryDto project) {
        var result = new Task(source.getDescription(), source.getDeadline(), project);
        result.setId(source.getId());
        result.setAdditionalComment(source.getAdditionalComment());
        result.setDone(source.isDone());
        return result;
    }
}
