package io.github.mat3e;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 07.12.2020
 */
public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
