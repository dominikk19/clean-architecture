package io.github.mat3e.task.dto;

import lombok.NonNull;

import java.time.ZonedDateTime;

public interface TaskWithChangesDto {
    int getId();

    @NonNull
    String getDescription();

    boolean isDone();

    ZonedDateTime getDeadline();

    int getChangesCount();

}
