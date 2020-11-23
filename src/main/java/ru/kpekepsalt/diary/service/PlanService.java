package ru.kpekepsalt.diary.service;

import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.TaskStatus;

import java.time.LocalDate;

public interface PlanService {
    void getPlan(LocalDate date, TaskStatus taskStatus, VoidParamActionFunctional<Plan> ok);
    Plan getPlan(LocalDate date, TaskStatus status);
    Plan getPlan(LocalDate date);
}
