package ru.kpekepsalt.diary.model;

import java.util.List;
import java.util.stream.Collector;
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

    public Plan filterPublic() {
        setPlan(
                plan.stream()
                .filter(task -> task.isPublic())
                .collect(Collectors.toList())
        );
        return this;
    }

    public boolean isEmpty() {
        return plan.isEmpty();
    }
}
