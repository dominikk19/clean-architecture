package io.github.mat3e.task.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 07.12.2020
 */
@RequiredArgsConstructor
@Getter
public class TaskCreator {
    private final TaskSourceId id;
    private final String description;
    private final ZonedDateTime deadline;
}
