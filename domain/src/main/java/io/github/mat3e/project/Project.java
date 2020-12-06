package io.github.mat3e.project;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "projects")
@Getter(AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private final Set<ProjectStep> steps = new HashSet<>();

    @PersistenceConstructor
    protected Project() {
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void addStep(ProjectStep step) {
        if (steps.contains(step)) {
            return;
        }
        steps.add(step);
        step.setProject(this);
    }

    void removeStep(ProjectStep step) {
        if (!steps.contains(step)) {
            return;
        }
        steps.remove(step);
        step.setProject(null);
    }

}
