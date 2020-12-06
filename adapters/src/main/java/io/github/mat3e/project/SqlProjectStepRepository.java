package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlProjectStepRepository extends Repository<SqlProjectStep, Integer> {
    void deleteById(int id);
}

@org.springframework.stereotype.Repository
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProjectStepRepositoryImpl implements ProjectStepRepository {
    private final SqlProjectStepRepository repository;

    @Override
    public void delete(ProjectStep entity) {
        repository.deleteById(entity.getId());
    }
}
