package io.github.mat3e.project;

import org.springframework.data.repository.Repository;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
interface SqlProjectQueryRepository extends ProjectQueryRepository, Repository<Project, Integer> {
}
