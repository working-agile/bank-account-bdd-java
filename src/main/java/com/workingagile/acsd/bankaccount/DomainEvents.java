package com.workingagile.acsd.bankaccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Minimal in-memory domain event publisher for this kata.
 * Allows tests to register handlers and the domain to publish events
 * without introducing external infrastructure.
 */
public final class DomainEvents {

    private static final Map<Class<?>, List<Consumer<?>>> subscribers = new ConcurrentHashMap<>();

    private DomainEvents() {}

    public static <T> void register(Class<T> eventType, Consumer<T> handler) {
        subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void publish(Object event) {
        if (event == null) return;
        var handlers = subscribers.get(event.getClass());
        if (handlers == null) return;
        for (Consumer handler : handlers) {
            handler.accept(event);
        }
    }

    public static void clearAllSubscribers() {
        subscribers.clear();
    }
}
