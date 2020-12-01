package io.github.mat3e.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
public class TaskDto {
    private int id;
    @NotNull
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private String additionalComment;

    public TaskDto() {
    }

    public TaskDto(Task source) {
        id = source.getId();
        description = source.getDescription();
        done = source.isDone();
        deadline = source.getDeadline();
        additionalComment = source.getAdditionalComment();
    }
}
