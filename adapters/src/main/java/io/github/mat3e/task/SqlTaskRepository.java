package io.github.mat3e.task;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlTaskRepository extends Repository<TaskSnapshot, Integer> {
    Optional<TaskSnapshot> findById(Integer id);

    <S extends TaskSnapshot> S save(S entity);

    <S extends TaskSnapshot> List<S> saveAll(Iterable<S> entities);

    void deleteById(Integer id);
}

interface SqlTaskQueryRepository extends TaskQueryRepository, Repository<TaskSnapshot, Integer> {
}

@org.springframework.stereotype.Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TaskRepositoryImpl implements TaskRepository {
    private final SqlTaskRepository sqlTaskRepository;

    @Override
    public Optional<Task> findById(int id) {
        return sqlTaskRepository.findById(id)
                .map(Task::restore);
    }

    @Override
    public Task save(Task entity) {
        return Task.restore(sqlTaskRepository.save(entity.getSnapshot()));
    }

    @Override
    public List<Task> saveAll(Iterable<Task> entities) {
        return sqlTaskRepository.saveAll(StreamSupport.stream(entities.spliterator(), false)
                .map(Task::getSnapshot)
                .collect(toList()))
                .stream()
                .map(Task::restore)
                .collect(toList());
    }

    @Override
    public void deleteById(int id) {
        sqlTaskRepository.deleteById(id);
    }
}
