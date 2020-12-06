package io.github.mat3e.project.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor
public
class ProjectDeadlineDto {
    private ZonedDateTime deadline;

}
