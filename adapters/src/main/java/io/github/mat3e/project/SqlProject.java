package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class SqlProject {

    static SqlProject fromProject(Project project) {
        var entitie = new SqlProject(project.getId(), project.getName());
        project.getSteps().stream()
                .map(step -> SqlProjectStep.fromStep(step, entitie))
                .forEach(entitie.steps::add);
        return entitie;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private final Set<SqlProjectStep> steps = new HashSet<>();


    private SqlProject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Project toProject() {
        var project = new Project(id, name);
        steps.forEach(sqlStep -> project.addStep(sqlStep.toStep(project)));
        return project;
    }
}
