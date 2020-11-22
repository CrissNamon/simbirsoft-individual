package ru.kpekepsalt.diary.service.Impl;

import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl implements PlanService {

    @Override
    public List<Task> getTasksWithStatus(List<Task> taskList, TaskStatus taskStatus) {
        return taskList
                .stream()
                .filter(task
                        ->
                        task.getTaskStatus()
                                .equals(taskStatus)
                )
                .collect(
                        Collectors.toList()
                );
    }

    @Override
    public List<Task> getTasksWithStatus(List<Task> taskList, String taskStatus) {
        return getTasksWithStatus(
                taskList,
                TaskStatus.valueOf(
                        taskStatus.toUpperCase()
                )
        );
    }

    @Override
    public List<Task> filterPublic(List<Task> taskList) {
        return taskList.stream()
                .filter(task -> task.isPublic())
                .collect(Collectors.toList());
    }
}
