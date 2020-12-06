package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 01.12.2020
 */
@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final ProjectInitializer initializer;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        initializer.init();
    }
}
