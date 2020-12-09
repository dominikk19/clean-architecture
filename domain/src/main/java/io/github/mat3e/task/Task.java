package io.github.mat3e.task;

import io.github.mat3e.task.vo.TaskCreator;
import io.github.mat3e.task.vo.TaskEvent;
import io.github.mat3e.task.vo.TaskSourceId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Task {

    private int id;
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private int changesCount;
    private String additionalComment;
    private final TaskSourceId sourceId;

    Task(String description, ZonedDateTime deadline, TaskSourceId sourceId) {
        this.description = description;
        this.deadline = deadline;
        this.sourceId = sourceId;
    }

    static Task restore(final TaskSnapshot taskSnapshot) {
        return new Task(taskSnapshot.getId(),
                taskSnapshot.getDescription(),
                taskSnapshot.isDone(),
                taskSnapshot.getDeadline(),
                taskSnapshot.getChangesCount(),
                taskSnapshot.getAdditionalComment(),
                taskSnapshot.getSourceId());
    }

    static Task createFrom(final TaskCreator taskCreator) {
        return new Task(
                0,
                taskCreator.getDescription(),
                false,
                taskCreator.getDeadline(),
                0,
                null,
                taskCreator.getId());
    }

    TaskEvent toggle() {
        done = !done;
        ++changesCount;
        return new TaskEvent(sourceId, null,
                done ? TaskEvent.State.DONE : TaskEvent.State.UNDONE);
    }

    TaskEvent updateInfo(final String description, final ZonedDateTime deadline, final String additionalComment) {
        this.description = description;
        this.deadline = deadline;
        this.additionalComment = additionalComment;
        return new TaskEvent(sourceId,
                new TaskEvent.Data(description, deadline, additionalComment),
                TaskEvent.State.UPDATE);

    }

    TaskSnapshot getSnapshot() {
        return new TaskSnapshot(id,
                description,
                done,
                deadline,
                changesCount,
                additionalComment,
                sourceId);
    }
}
