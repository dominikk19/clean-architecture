package io.github.mat3e.task;

import io.github.mat3e.project.dto.SimpleProject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Task {

    static Task restore(TaskSnapshot taskSnapshot) {
        return new Task(taskSnapshot.getId(),
                taskSnapshot.getDescription(),
                taskSnapshot.isDone(),
                taskSnapshot.getDeadline(),
                taskSnapshot.getChangesCount(),
                taskSnapshot.getAdditionalComment(),
                taskSnapshot.getProject() != null ? SimpleProject.restore(taskSnapshot.getProject()) : null);
    }


    private int id;
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private int changesCount;
    private String additionalComment;
    private final SimpleProject project;

    Task(String description, ZonedDateTime deadline, SimpleProject project) {
        this.description = description;
        this.deadline = deadline;
        this.project = project;
    }

    void toggle() {
        done = !done;
        ++changesCount;
    }

    void updateInfo(final String description, final ZonedDateTime deadline, final String additionalComment) {
        if (!done) {
            this.description = description;
            this.deadline = deadline;
            this.additionalComment = additionalComment;
        }
    }

    TaskSnapshot getSnapshot() {
        return new TaskSnapshot(id,
                description,
                done,
                deadline,
                changesCount,
                additionalComment,
                project.getSnapshot() != null ? project.getSnapshot() : null);
    }
}
