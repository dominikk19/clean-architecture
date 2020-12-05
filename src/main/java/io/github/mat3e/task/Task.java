package io.github.mat3e.task;

import io.github.mat3e.project.query.SimpleProjectQueryDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tasks")
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class Task {
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
    private SimpleProjectQueryDto project;

    @PersistenceConstructor
    public Task() {
    }

    Task(String description, ZonedDateTime deadline, SimpleProjectQueryDto project) {
        this.description = description;
        this.deadline = deadline;
        this.project = project;
    }

    TaskDto toDto() {
        return TaskDto.builder()
                .withId(id)
                .withDescription(description)
                .withDone(done)
                .withDeadline(deadline)
                .withAdditionalComment(additionalComment)
                .build();
    }

}
