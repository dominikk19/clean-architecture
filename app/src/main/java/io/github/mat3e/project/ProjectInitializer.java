package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Set;

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
            projectRepository.save(
                    Project.restore(
                            new ProjectSnapshot(
                                    0,
                                    "Example project",
                                    Set.of(
                                            new ProjectStepSnapshot(0, "First", -3),
                                            new ProjectStepSnapshot(0, "Second", -2),
                                            new ProjectStepSnapshot(0, "Third", 0)
                                    )
                            )
                    )

            );

        }
    }
}
