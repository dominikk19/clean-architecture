package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectDeadlineDto {
    private ZonedDateTime deadline;

}
