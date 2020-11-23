package ru.kpekepsalt.diary.model;

import java.util.List;
import java.util.stream.Collectors;

public class Plan {

    private List<Task> plan;

    public Plan() {}

    public Plan(List<Task> taskList) {
        this.plan = taskList;
    }

    public List<Task> getPlan() {
        return plan;
    }

    public void setPlan(List<Task> plan) {
        this.plan = plan;
    }

    public boolean isEmpty() {
        return plan.isEmpty();
    }

    public void filterByStatus(TaskStatus taskStatus) {
        plan
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

    public void filterByStatus(String taskStatus) {
        filterByStatus(
                TaskStatus.valueOf(
                        taskStatus.toUpperCase()
                )
        );
    }
}
