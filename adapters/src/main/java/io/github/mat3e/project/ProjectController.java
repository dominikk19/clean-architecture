package io.github.mat3e.project;

import com.google.common.collect.Lists;
import io.github.mat3e.project.dto.ProjectDeadlineDto;
import io.github.mat3e.project.dto.ProjectDto;
import io.github.mat3e.task.dto.TaskDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectController {
    private final ProjectFacade projectFacade;
    private final ProjectQueryRepository projectQueryRepository;


    @GetMapping
    List<ProjectDto> list() {
        return Lists.newArrayList(projectQueryRepository.findBy(ProjectDto.class));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDto> get(@PathVariable int id) {
        return projectQueryRepository.findDtoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ProjectDto> update(@PathVariable int id, @RequestBody ProjectDto toUpdate) {
        if (id != toUpdate.getId() && toUpdate.getId() != 0) {
            throw new IllegalStateException("Id in URL is different than in body: " + id + " and " + toUpdate.getId());
        }
        projectFacade.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ResponseEntity<ProjectDto> create(@RequestBody ProjectDto toCreate) {
        var result = projectFacade.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/{id}/tasks")
    List<TaskDto> createTasks(@PathVariable int id, @RequestBody ProjectDeadlineDto deadlineDto) {
        return projectFacade.createTasks(id, deadlineDto.getDeadline());
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> handleClientError(IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
