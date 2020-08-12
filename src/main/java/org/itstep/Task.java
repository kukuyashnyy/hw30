package org.itstep;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Task implements Serializable {

    private String name;
    private int priority;
    private boolean complete;

    public Task(String name, int priority) {
        this(name, priority, false);
    }

    public Task(String name, int priority, boolean complete) {
        this.name = name;
        this.priority = priority;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setComplete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public boolean equals(Object obj) {
        Task task = (Task) obj;
        return (name.matches(task.getName()) & (priority == task.priority));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                ", complete=" + complete +
                '}';
    }
}


