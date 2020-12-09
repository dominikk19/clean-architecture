package io.github.mat3e.project;

import io.github.mat3e.task.vo.TaskEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 09.12.2020
 */
@Service
@RequiredArgsConstructor
class ProjectEventListener {
    private final ProjectFacade projectFacade;

    @EventListener
    public void on(TaskEvent taskEvent) {
        projectFacade.handle(taskEvent);
    }
}
