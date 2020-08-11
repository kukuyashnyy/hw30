package org.itstep;

import java.util.Objects;

public class Task {

    private String name;
    private int priority;

    public Task(String name) {
        this(name, 0);
    }

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        Task task = (Task) obj;
        return (name.matches(task.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


