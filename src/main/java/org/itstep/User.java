package org.itstep;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String name;
    private String password;
    private Collection<Task> taskList;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.taskList = new ArrayList<>();
    }

    public Collection<Task> getTaskList() {
        return taskList;
    }

    public void completeTask(Task task) {
        if (this.taskList.contains(task)) {
            System.out.println("Задача отмеченна как выполненна.");
        } else {
            System.out.println("Заданная задача не нейдена.");
        }
    }

    public void add(Task task) {
        taskList.add(task);
        taskList.forEach(System.out::println);
    }

    public Task del(Task task) {
        if(taskList.contains(task)){
            taskList.remove(task);
        } else {
            System.out.println("Задача не найдена!");
        }
        return task;
    }

    @Override
    public boolean equals(Object object) {
        User user = (User) object;
        return (this.name.matches(user.getName())
        & this.password.matches(user.getPassword()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
