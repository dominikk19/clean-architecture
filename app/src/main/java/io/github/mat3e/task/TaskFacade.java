package io.github.mat3e.task;

import io.github.mat3e.DomainEventPublisher;
import io.github.mat3e.task.dto.TaskDto;
import io.github.mat3e.task.vo.TaskCreator;
import io.github.mat3e.task.vo.TaskEvent;
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
    private final DomainEventPublisher publisher;


    public List<TaskDto> createTasks(Collection<TaskCreator> tasks) {
        return taskRepository.saveAll(
                tasks.stream()
                        .map(Task::createFrom)
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
                                        publisher.publish(existingTask.toggle());
                                    }
                                    publisher.publish(existingTask.updateInfo(toSave.getDescription(), toSave.getDeadline(), toSave.getAdditionalComment()));
                                    return existingTask;
                                }).orElseGet(() -> taskFactory.from(toSave))
                ));
    }

    void delete(int id) {
        taskRepository.findById(id)
                .ifPresent(task -> {
                    taskRepository.deleteById(id);
                    publisher.publish(new TaskEvent(
                            task.getSnapshot().getSourceId(),
                            null,
                            TaskEvent.State.DELETED

                    ));
                });
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
