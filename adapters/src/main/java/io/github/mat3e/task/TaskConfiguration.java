package io.github.mat3e.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@Configuration
class TaskConfiguration {
    @Bean
    TaskFacade taskFacade(TaskRepository taskRepository) {
        return new TaskFacade(taskRepository, new TaskFactory());
    }
}
