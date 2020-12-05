package io.github.mat3e.task;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@Component("taskWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final TaskRepository taskRepository;

    Warmup(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (taskRepository.count() == 0) {
            var task = new Task("Example task", ZonedDateTime.now(), null);
            taskRepository.save(task);
        }
    }
}
