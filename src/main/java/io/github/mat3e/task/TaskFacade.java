package io.github.mat3e.task;

import io.github.mat3e.project.dto.SimpleProjectQueryEntity;
import io.github.mat3e.task.dto.TaskDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TaskFacade {
    private final TaskRepository taskRepository;
    private final TaskFactory taskFactory;


    public List<TaskDto> saveAll(Collection<TaskDto> tasks, SimpleProjectQueryEntity project) {
        return taskRepository.saveAll(tasks.stream()
                .map(dto -> taskFactory.from(dto, project))
                .collect(toList()))
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }


    TaskDto save(TaskDto toSave) {
        return
                taskRepository.save(
                        taskRepository.findById(toSave.getId())
                                .map(existingTask -> {
                                    if (existingTask.isDone() != toSave.isDone()) {
                                        existingTask.setChangesCount(existingTask.getChangesCount() + 1);
                                        existingTask.setDone(toSave.isDone());
                                    }
                                    existingTask.setAdditionalComment(toSave.getAdditionalComment());
                                    existingTask.setDeadline(toSave.getDeadline());
                                    existingTask.setDescription(toSave.getDescription());
                                    return existingTask;
                                }).orElseGet(() -> {
                            var result = new Task(toSave.getDescription(), toSave.getDeadline(), null);
                            result.setAdditionalComment(toSave.getAdditionalComment());
                            return result;
                        })
                ).toDto();
    }

    void delete(int id) {
        taskRepository.deleteById(id);
    }


}
