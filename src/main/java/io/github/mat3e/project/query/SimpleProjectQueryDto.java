package io.github.mat3e.project.query;

import lombok.Getter;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project java-clean-architecture
 * @date 05.12.2020
 */

@Entity
@Table(name = "projects")
@Getter
public class SimpleProjectQueryDto {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;

    @PersistenceConstructor
    protected SimpleProjectQueryDto() {
    }

    public SimpleProjectQueryDto(final int id, final String name) {
        this.id = id;
        this.name = name;
    }
}
