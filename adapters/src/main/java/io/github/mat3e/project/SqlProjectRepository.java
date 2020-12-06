package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlProjectRepository extends Repository<SqlProject, Integer> {
    SqlProject save(SqlProject project);

    Optional<SqlProject> findById(Integer id);
}


@org.springframework.stereotype.Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectRepositoryImpl implements ProjectRepository {
    private final SqlProjectRepository sqlProjectRepository;

    @Override
    public Optional<Project> findById(int id) {
        return sqlProjectRepository.findById(id)
                .map(SqlProject::toProject);
    }

    @Override
    public Project save(final Project project) {
        return sqlProjectRepository.save(SqlProject.fromProject(project))
                .toProject();
    }
}
