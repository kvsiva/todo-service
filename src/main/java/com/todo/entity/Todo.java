package com.todo.entity;

public class Todo {

    private int id;

    private String taskName;

    private String priority;

    public Todo(int id, String taskName, String priority) {
        this.id = id;
        this.taskName = taskName;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
