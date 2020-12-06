package io.github.mat3e.task;

import io.github.mat3e.project.SqlSimpleProject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class SqlTask {
    static SqlTask fromTask(Task task) {
        return new SqlTask(task.getId(),
                task.getDescription(),
                task.isDone(),
                task.getDeadline(),
                task.getChangesCount(),
                task.getAdditionalComment(),
                task.getProject() == null ? null : SqlSimpleProject.fromProject(task.getProject()));
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    @NotNull
    private String description;
    private boolean done;
    private ZonedDateTime deadline;
    private int changesCount;
    private String additionalComment;
    @ManyToOne
    @JoinColumn(name = "source_id")
    private SqlSimpleProject project;

    Task toTask() {
        return new Task(id,
                description,
                done,
                deadline,
                changesCount,
                additionalComment,
                project == null ? null : project.toProject());
    }
}
