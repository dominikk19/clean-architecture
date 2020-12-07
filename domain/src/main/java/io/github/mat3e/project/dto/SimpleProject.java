package io.github.mat3e.project.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleProject {

    public static SimpleProject restore(final SimpleProjectSnapshot project) {
        return new SimpleProject(project.getId(), project.getName());
    }

    private final int id;
    private final String name;

    public SimpleProjectSnapshot getSnapshot() {
        return new SimpleProjectSnapshot(id, name);
    }
}
