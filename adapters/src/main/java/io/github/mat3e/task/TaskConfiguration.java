package io.github.mat3e.task;

import io.github.mat3e.DomainEventPublisher;
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
    TaskFacade taskFacade(TaskRepository taskRepository, DomainEventPublisher domainEventPublisher) {
        return new TaskFacade(taskRepository, new TaskFactory(), domainEventPublisher);
    }

    @Bean
    TaskInitializer taskInitializer(final TaskRepository taskRepository, final TaskQueryRepository taskQueryRepository) {
        return new TaskInitializer(taskRepository, taskQueryRepository);
    }
}
