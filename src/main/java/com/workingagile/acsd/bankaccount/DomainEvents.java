package com.workingagile.acsd.bankaccount;

import java.util.function.Consumer;

/**
 * Domain event publisher contract. Implementations decide how to store and
 * dispatch subscribers. This allows injecting a publisher into domain objects
 * instead of relying on static globals.
 */
public interface DomainEvents {
    <T> void register(Class<T> eventType, Consumer<T> handler);
    void publish(Object event);
    void clear();
}
