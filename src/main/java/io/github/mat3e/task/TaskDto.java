package io.github.mat3e.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonDeserialize(builder = TaskDto.Builder.class)
public class TaskDto {

    public static Builder builder() {
        return new Builder();
    }

    private final int id;
    @NotNull
    private final String description;
    private final boolean done;
    private final ZonedDateTime deadline;
    private final String additionalComment;

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        private int id;
        private String description;
        private boolean done;
        private ZonedDateTime deadline;
        private String additionalComment;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDone(boolean done) {
            this.done = done;
            return this;
        }

        public Builder withDeadline(ZonedDateTime deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder withAdditionalComment(String additionalComment) {
            this.additionalComment = additionalComment;
            return this;
        }

        public TaskDto build() {
            return new TaskDto(id, description, done, deadline, additionalComment);
        }
    }
}
