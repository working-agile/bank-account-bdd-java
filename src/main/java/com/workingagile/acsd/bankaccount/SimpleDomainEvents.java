package com.workingagile.acsd.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Simple in-memory implementation of DomainEvents for tests and demos.
 */
public class SimpleDomainEvents implements DomainEvents {

    private final Map<Class<?>, List<Consumer<?>>> subscribers = new ConcurrentHashMap<>();

    @Override
    public <T> void register(Class<T> eventType, Consumer<T> handler) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void publish(Object event) {
        if (event == null) return;
        var handlers = subscribers.get(event.getClass());
        if (handlers == null) return;
        for (Consumer handler : handlers) {
            handler.accept(event);
        }
    }

    @Override
    public void clear() {
        subscribers.clear();
    }
}
