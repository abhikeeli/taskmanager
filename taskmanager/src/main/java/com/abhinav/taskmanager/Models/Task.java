package com.abhinav.taskmanager.Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    private static final AtomicInteger count=new AtomicInteger(0);
    private int id;
    private String name;
    private String status="pending";
    private int prirority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrirority() {
        return prirority;
    }

    public void setPrirority(int prirority) {
        this.prirority = prirority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task( String name, int prirority) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.prirority = prirority;
        this.status = "pending";
    }
    public Task( String name) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.prirority = 5;
        this.status = "pendding";
    }
    public Task(){
        this.id=count.incrementAndGet();
    }
}
