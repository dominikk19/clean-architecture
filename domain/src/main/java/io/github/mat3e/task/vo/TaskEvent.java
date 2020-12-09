package io.github.mat3e.task.vo;

import io.github.mat3e.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 07.12.2020
 */
@Getter
public class TaskEvent implements DomainEvent {
    private final Instant occurredOn;
    private final TaskSourceId sourceId;
    private final Data data;
    private final State state;

    public TaskEvent(final TaskSourceId sourceId, final Data data, final State state) {
        this.occurredOn = Instant.now();
        this.sourceId = sourceId;
        this.data = data;
        this.state = state;
    }

    @Override
    public Instant getOccurredOn() {
        return occurredOn;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Data {
        private final String description;
        private final ZonedDateTime deadline;
        private final String additionalComment;
    }

    public enum State {
        DONE, UNDONE, UPDATE, DELETED
    }
}
