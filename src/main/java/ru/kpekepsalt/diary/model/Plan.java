package ru.kpekepsalt.diary.model;

import java.util.List;

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

}
