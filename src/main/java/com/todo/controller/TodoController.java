package com.todo.controller;

import com.todo.entity.Todo;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RequestMapping("/todos")
@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("getPlannedTasks")
    public List<Todo> getAllTasks() {
        return todoService.findPlannedTasks();
    }

    @GetMapping("getInProgressTasks")
    public List<Todo> getInProgressTasks() {
        return todoService.findInProgressTasks();
    }

    @GetMapping("getDoneTasks")
    public List<Todo> getDoneTasks() {
        return todoService.findDoneTasks();
    }

    @DeleteMapping("deleteTaskPlanned/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        todoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteTaskProgress/{id}")
    public ResponseEntity<Void> deleteTaskInProgess(@PathVariable int id) {
        todoService.deleteInProgressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getTask/{id}")
    public Todo getTask(@PathVariable int id) {
        return todoService.findById(id);
    }

    @PutMapping("updateTask/{id}")
    public ResponseEntity<Todo> updateTask(@RequestBody Todo todo,@PathVariable Integer id) {
        Todo todoUpdated = todoService.update(todo,id);
        return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);
    }

    @PostMapping("createTask")
    public ResponseEntity<Todo> createTask(@RequestBody Todo todo) {
        Todo todoCreated = todoService.save(todo);
        return new ResponseEntity<Todo>(todoCreated, HttpStatus.CREATED);
    }

    @PostMapping("movePlannedToInProgress")
    public void movePlannedToInProgress(@RequestBody Todo todo) {
        todoService.movePlanToProgress(todo);
    }

    @PostMapping("moveInProgressToDone")
    public void moveInProgressToDone(@RequestBody Todo todo) {
        todoService.moveIngressToDone(todo);
    }
}
