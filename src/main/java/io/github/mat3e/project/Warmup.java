package io.github.mat3e.project;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 01.12.2020
 */
@Component("projectWarmup")
@RequiredArgsConstructor
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final ProjectRepository projectRepository;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (projectRepository.count() == 0) {
            var project = new Project();
            project.setName("Example project");
            project.addStep(new ProjectStep("First", -3, project));
            project.addStep(new ProjectStep("Second", -2, project));
            project.addStep(new ProjectStep("Third", 0, project));
            projectRepository.save(project);
        }
    }
}
