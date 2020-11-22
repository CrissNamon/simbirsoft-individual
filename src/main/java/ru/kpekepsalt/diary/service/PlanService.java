package ru.kpekepsalt.diary.service;

import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.model.TaskStatus;

import java.util.List;

public interface PlanService {

    List<Task> getTasksWithStatus(List<Task> taskList, TaskStatus taskStatus);
    List<Task> getTasksWithStatus(List<Task> taskList, String taskStatus);
    List<Task> filterPublic(List<Task> taskList);
}
