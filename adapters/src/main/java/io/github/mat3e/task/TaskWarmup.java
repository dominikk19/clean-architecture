package io.github.mat3e.task;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TaskWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final TaskInitializer taskInitializer;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        taskInitializer.init();
    }
}
