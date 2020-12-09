package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlProjectRepository extends Repository<ProjectSnapshot, Integer> {
    ProjectSnapshot save(ProjectSnapshot project);

    Optional<ProjectSnapshot> findById(Integer id);

    @Query(nativeQuery = true,
            value = "select distinct * from projects p join project_steps s on p.id = s.project_id where s.id = :stepId"
    )
    Optional<Project> findWithNestedStepId(@Param("stepId") Integer stepId);
}

interface SqlProjectQueryRepository extends ProjectQueryRepository, Repository<ProjectSnapshot, Integer> {
}

interface SqlProjectStepRepository extends Repository<ProjectStepSnapshot, Integer> {
    void deleteById(int id);
}


@org.springframework.stereotype.Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectRepositoryImpl implements ProjectRepository {
    private final SqlProjectRepository projectRepository;
    private final SqlProjectStepRepository stepRepository;

    @Override
    public Optional<Project> findById(final int id) {
        return projectRepository.findById(id)
                .map(Project::restore);
    }

    @Override
    public Project save(final Project project) {
        return Project.restore(projectRepository.save(project.getSnapshot()));
    }

    @Override
    public void delete(final Project.Step entity) {
        stepRepository.deleteById(entity.getSnapshot().getId());
    }

    @Override
    public Optional<Project> findByNestedStepId(Integer stepId) {
        return projectRepository.findWithNestedStepId(stepId);
    }
}
