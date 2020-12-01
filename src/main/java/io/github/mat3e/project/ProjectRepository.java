package io.github.mat3e.project;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface ProjectRepository extends Repository<Project, Integer> {

    Project save(Project entity);

    Optional<Project> findById(int id);

    List<Project> findAll();

    long count();

}
