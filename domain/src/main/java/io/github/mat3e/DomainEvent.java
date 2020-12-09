package io.github.mat3e;

import java.time.Instant;

/**
 * @author Dominik Kiszka {dominikk19}
 * @project JavaCleanArchitecture
 * @date 07.12.2020
 */
public interface DomainEvent {
    Instant getOccurredOn();
}
