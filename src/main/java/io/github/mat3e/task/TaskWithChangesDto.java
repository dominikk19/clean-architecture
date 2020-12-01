package io.github.mat3e.task;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class TaskWithChangesDto {
    private int id;
    @NotNull
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private int changesCount;

    TaskWithChangesDto(Task source) {
        id = source.getId();
        description = source.getDescription();
        done = source.isDone();
        deadline = source.getDeadline();
        changesCount = source.getChangesCount();
    }
}
