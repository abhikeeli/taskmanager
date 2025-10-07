package com.abhinav.taskmanager.Controller;

import com.abhinav.taskmanager.Models.Task;
import com.abhinav.taskmanager.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping(" ")
    public List<Task> getAllTask(){
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable int id){
        return taskService.getByid(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id){
        boolean removed =taskService.deleteTask(id);
        return removed ? "Task Deleted" : "Task not found";
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeAllTasks(){
        return ResponseEntity.ok(taskService.executeAll());
    }
}
