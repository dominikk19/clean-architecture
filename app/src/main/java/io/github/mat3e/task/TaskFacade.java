package io.github.mat3e.task;

import io.github.mat3e.project.dto.SimpleProject;
import io.github.mat3e.task.dto.TaskDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TaskFacade {
    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;


    public List<TaskDto> saveAll(Collection<TaskDto> tasks, SimpleProject project) {
        return taskRepository.saveAll(tasks.stream()
                .map(dto -> taskFactory.from(dto, project))
                .collect(toList()))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    TaskDto save(TaskDto toSave) {
        return
                toDto(taskRepository.save(
                        taskRepository.findById(toSave.getId())
                                .map(existingTask -> {
                                    if (existingTask.getSnapshot().isDone() != toSave.isDone()) {
                                        existingTask.toggle();
                                    }
                                    existingTask.updateInfo(toSave.getDescription(), toSave.getDeadline(), toSave.getAdditionalComment());
                                    return existingTask;
                                }).orElseGet(() -> taskFactory.from(toSave, null))
                ));
    }

    void delete(int id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toDto(Task task) {
        var snapshot = task.getSnapshot();
        return TaskDto.builder()
                .withId(snapshot.getId())
                .withDescription(snapshot.getDescription())
                .withDone(snapshot.isDone())
                .withDeadline(snapshot.getDeadline())
                .withAdditionalComment(snapshot.getAdditionalComment())
                .build();
    }

}
