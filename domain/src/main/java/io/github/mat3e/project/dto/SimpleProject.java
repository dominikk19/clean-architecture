package io.github.mat3e.project.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */

@Getter
@RequiredArgsConstructor
public class SimpleProject {

    private final int id;
    private final String name;
}
