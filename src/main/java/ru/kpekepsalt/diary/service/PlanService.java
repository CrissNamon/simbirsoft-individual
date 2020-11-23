package ru.kpekepsalt.diary.service;

import org.springframework.http.ResponseEntity;
import ru.kpekepsalt.diary.functional.ParamResponseFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.TaskStatus;

import java.time.LocalDate;

public interface PlanService {

    ResponseEntity<Plan> getPlan(LocalDate date, TaskStatus taskStatus, ParamResponseFunctional<Plan> ok);
}
