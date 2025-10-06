package com.abhinav.taskmanager.Service;

import com.abhinav.taskmanager.Models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final List<Task> taskQueue=new ArrayList<>();
    private int counter =1;

    public Task addTask(Task task){
        taskQueue.add(task);
        return task;
    }

    public List<Task> getAllTask() {
        return taskQueue;
    }
    public Task getByid(int id){
        return taskQueue.stream().filter(task->task.getId()==id).findFirst().orElse(null);
    }
    public String executeTasksSequentially(){
        StringBuilder sb =new StringBuilder();
        for(Task task : taskQueue){
            sb.append("Execute Task : ")
                    .append(task.getName())
                    .append(" | Status : ")
                    .append(task.getStatus())
                    .append("\n");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                sb.append(" Task interrupted : ");
                sb.append(task.getName()).append("\n");
            }
            task.setStatus("done");
        }
        return sb.toString();
    }
    public boolean deleteTask(int id){
        return  taskQueue.removeIf(task -> task.getId()==id);
    }
}
