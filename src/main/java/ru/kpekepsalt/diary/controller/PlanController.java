package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;
import ru.kpekepsalt.diary.service.ResponseService;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private ResponseService<Plan> responseService;

    /**
     * @return List of tasks for today
     */
    @GetMapping("/")
    @PreAuthorize("hasAuthority('plan:get:now')")
    public ResponseEntity<Plan> getDayPlan() {
        return planService.getPlan(
                LocalDate.now(),
                null,
                plan -> responseService.ok(plan)
        );
    }

    /**
     * @param date Date for plan
     * @return List of tasks for given date
     */
    @GetMapping("/{date}")
    @PreAuthorize("hasAuthority('plan:get:date')")
    public ResponseEntity<Plan> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return planService.getPlan(
                date,
                null,
                plan -> responseService.ok(plan)
        );
    }

    /**
     * @param date Plan date
     * @param taskStatus Task status
     * @return List of tasks for given date with given status
     */
    @GetMapping("/{date}/{status}")
    public ResponseEntity<Plan> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("status") String taskStatus) {
        return planService.getPlan(
                date,
                TaskStatus.valueOf(taskStatus.toUpperCase()),
                plan -> responseService.ok(plan)
        );
    }

}
