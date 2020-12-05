package io.github.mat3e.task;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface TaskRepository extends Repository<Task, Integer> {
    List<Task> findAllByProject_Id(int id);

    List<Task> findAll();

    Optional<Task> findById(int id);

    Task save(Task entity);

    <S extends Task> List<S> saveAll(Iterable<S> entities);

    void deleteById(int id);

    long count();
}
