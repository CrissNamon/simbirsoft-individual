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

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * @return List of tasks for today
     */
    @GetMapping("/")
    @PreAuthorize("hasAuthority('plan:get:now')")
    public ResponseEntity<Plan> getDayPlan() {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference = new AtomicReference<>();
        planService.getPlan(
                LocalDate.now(),
                null,
                plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param date Date for plan
     * @return List of tasks for given date
     */
    @GetMapping("/{date}")
    @PreAuthorize("hasAuthority('plan:get:date')")
    public ResponseEntity<Plan> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference = new AtomicReference<>();
        planService.getPlan(
                date,
                null,
                plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param date Plan date
     * @param taskStatus Task status
     * @return List of tasks for given date with given status
     */
    @GetMapping("/{date}/{status}")
    public ResponseEntity<Plan> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("status") String taskStatus) {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference = new AtomicReference<>();
        try {
            planService.getPlan(
                    date,
                    TaskStatus.valueOf(taskStatus.toUpperCase()),
                    plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
            );
        }catch(IllegalArgumentException e){
            responseEntityAtomicReference.set(ResponseEntity.badRequest().build());
        }
        return responseEntityAtomicReference.get();
    }

}
