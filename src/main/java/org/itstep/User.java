package org.itstep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class User {
    private String name;
    private String password;
    private Collection<Task> taskList;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task del(Task task) {
        taskList.remove(task);
        return task;
    }
}
