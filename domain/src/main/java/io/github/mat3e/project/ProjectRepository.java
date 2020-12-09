package io.github.mat3e.project;

import java.util.Optional;

interface ProjectRepository {

    Optional<Project> findById(int id);

    Optional<Project> findByNestedStepId(Integer stepId);

    Project save(Project entity);

    void delete(Project.Step entity);
}
