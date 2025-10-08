package com.abhinav.taskmanager.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;

@Service
public class LockManager {
    private Semaphore semaphore=new Semaphore(1);
    public void acquire(){
        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted while waiting for lock");
        }
    }
    public void  release(){
        semaphore.release();
    }
}
