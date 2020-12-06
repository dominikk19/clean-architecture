package io.github.mat3e.project;


import io.github.mat3e.project.dto.SimpleProject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 06.12.2020
 */
@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SqlSimpleProject {
    public static SqlSimpleProject fromProject(SimpleProject simpleProject) {
        return new SqlSimpleProject(simpleProject.getId(), simpleProject.getName());
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;

    private SqlSimpleProject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleProject toProject() {
        return new SimpleProject(id, name);
    }
}
