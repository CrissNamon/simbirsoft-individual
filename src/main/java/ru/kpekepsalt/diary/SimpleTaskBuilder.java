package ru.kpekepsalt.diary;

import ru.kpekepsalt.diary.entities.ITask;
import ru.kpekepsalt.diary.entities.ITaskBuilder;

import java.util.Date;

public class SimpleTaskBuilder implements ITaskBuilder {

    protected SimpleTask task;

    public SimpleTaskBuilder() {
        task = new SimpleTask();
    }

    @Override
    public ITaskBuilder setStartDate(Date date) {
        task.setStartDate(date);
        return this;
    }

    @Override
    public ITaskBuilder setTitle(String title) {
        task.setTitle(title);
        return this;
    }

    /**
     * @param date Дата окончания задачи
     */
    public SimpleTaskBuilder setEndDate(Date date) {
        task.setDateEnd(date);
        return this;
    }

    @Override
    public ITask build() {
        return task;
    }



}
