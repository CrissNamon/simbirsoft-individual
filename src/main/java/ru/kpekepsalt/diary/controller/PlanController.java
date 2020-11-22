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
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.Impl.UserDetailsServiceImpl;
import ru.kpekepsalt.diary.service.PlanService;
import ru.kpekepsalt.diary.service.TaskService;

import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;


@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PlanService planService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * @return List of tasks for today
     */
    @GetMapping("/")
    @PreAuthorize("hasAuthority('plan:get:now')")
    public ResponseEntity<List<Task>> getDayPlan() {
        return getDayPlan(LocalDate.now());
    }

    /**
     * @param date Date for plan
     * @return List of tasks for given date
     */
    @GetMapping("/{date}")
    @PreAuthorize("hasAuthority('plan:get:date')")
    public ResponseEntity<List<Task>> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if(isEmpty(date)) {
            return ResponseEntity.badRequest().build();
        }
        Plan plan = taskService.findByUserIdAndDate(
                userDetailsService.getUserid(),
                date
        );
        if(plan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if(!userDetailsService.hasAuthority("task:get:private") && !userDetailsService.getUserid().equals(userDetailsService.getUserid())) {
            plan = plan.filterPublic();
        }
        return ResponseEntity.ok(plan.getPlan());
    }

    /**
     * @param date Plan date
     * @param taskStatus Task status
     * @return List of tasks for given date with given status
     */
    @GetMapping("/{date}/{status}")
    public ResponseEntity<List<Task>> getDayPlan(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("status") String taskStatus) {
        if(isEmpty(date)) {
            return ResponseEntity.badRequest().build();
        }
        List<Task> tasks = planService.getTasksWithStatus(
                getDayPlan(date).getBody(),
                taskStatus
        );
        Plan plan = new Plan(tasks);
        if(plan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

}
