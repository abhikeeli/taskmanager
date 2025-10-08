package com.abhinav.taskmanager.Service;

import com.abhinav.taskmanager.Models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TaskService{
    private final List<Task> taskQueue=new ArrayList<>();
    private final LockManager lockManager;
    private int completedTasks =0;

    public TaskService(LockManager lockManager) {
        this.lockManager = lockManager;
    }

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
    public String executeAll(){

        ExecutorService executor=Executors.newFixedThreadPool(3);
        List<Future<String>> futures=new ArrayList<>();
        for(Task task : taskQueue){
            futures.add((Future<String>) executor.submit(()->executeTask(task)));
        }
        StringBuilder result=new StringBuilder();
        for(Future<String> f : futures){
            try {
                result.append(f.get()).append("\n");
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
        return result.toString();
    }
    public String executeTask(Task task){
        lockManager.acquire();
        StringBuilder sb =new StringBuilder();
            sb.append("Execute Task : ")
                    .append(task.getName())
                    .append(" | Status : ")
                    .append(task.getStatus())
                    .append("\n");
            try{
                Thread.sleep(1000);
                completedTasks++;
                System.out.println("completed tasks : " + completedTasks);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                sb.append(" Task interrupted : ");
                sb.append(task.getName()).append("\n");
            }
            finally {
                lockManager.release();
            }
            task.setStatus("done");
        return sb.toString();
    }
    public boolean deleteTask(int id){
        return  taskQueue.removeIf(task -> task.getId()==id);
    }
}
