package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectInitializer {
    private final ProjectRepository projectRepository;
    private final ProjectQueryRepository projectQueryRepository;

    void init() {
        if (projectQueryRepository.count() == 0) {
            var project = new Project();
            project.setName("Example project");
            project.addStep(new ProjectStep("First", -3, project));
            project.addStep(new ProjectStep("Second", -2, project));
            project.addStep(new ProjectStep("Third", 0, project));
            projectRepository.save(project);
        }
    }
}
