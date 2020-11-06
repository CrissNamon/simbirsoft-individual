package ru.kpekepsalt.diary.entities;

import java.util.Date;

public abstract class Task implements ITask{

    protected String title;
    protected Date dateStart;
    protected Long id;

    public Task() {}

    public Task(String title, Date date) {
        this.title = title;
        this.dateStart = date;
        this.id = Long.valueOf(this.hashCode());
    }

    @Override
    public Date getStartDate() {
        return dateStart;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setStartDate(Date date) {
        this.dateStart = date;
    }
}
