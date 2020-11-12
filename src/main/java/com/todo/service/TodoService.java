package com.todo.service;

import com.todo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class TodoService {

    ConcurrentMap<Integer, Todo> plannedTasks=new ConcurrentHashMap<>();
    ConcurrentMap<Integer, Todo> inProgressTasks=new ConcurrentHashMap<>();
    ConcurrentMap<Integer, Todo> doneTasks=new ConcurrentHashMap<>();
    private static int idCounter = 0;

    public List<Todo> findPlannedTasks() {
        return new ArrayList<>(plannedTasks.values());
    }

    public List<Todo> findInProgressTasks() {
        return new ArrayList<>(inProgressTasks.values());
    }

    public List<Todo> findDoneTasks() {
        return new ArrayList<>(doneTasks.values());
    }

    public Todo findById(int id) {
        return plannedTasks.get(id);
    }

    public Todo findPlannedTasksById(int id) {
        return plannedTasks.get(id);
    }

    public Todo findInProgressTasksById(int id) {
        return inProgressTasks.get(id);
    }

    public void deleteById(int id) {
        plannedTasks.remove(id);
    }

    public void deleteInProgressById(int id) {
        inProgressTasks.remove(id);
     }

    public Todo save(Todo todo) {
        todo.setId(++idCounter);
        plannedTasks.put(todo.getId(),todo);
        return todo;
    }

    public Todo saveInProgessTasks(Todo todo) {
        inProgressTasks.put(todo.getId(),todo);
        return todo;
    }

    public Todo saveDoneTasks(Todo todo) {
        doneTasks.put(todo.getId(),todo);
        return todo;
    }


    public Todo update(Todo todo,Integer id) {
        plannedTasks.put(id,todo);
        return todo;
    }

    public void movePlanToProgress(Todo todo) {
        deleteById(todo.getId());
        saveInProgessTasks(todo);
    }

    public void moveIngressToDone(Todo todo) {
        deleteInProgressById(todo.getId());
        saveDoneTasks(todo);
    }
}