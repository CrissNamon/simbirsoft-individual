package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;
import ru.kpekepsalt.diary.service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;


@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PlanService planService;

    /**
     * @param date Plan date
     * @return List of tasks for given date
     */
    @GetMapping("/{date}")
    public ResponseEntity<List<Task>> getDayPlan(@PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) {
        if(isEmpty(date)) {
            return ResponseEntity.badRequest().build();
        }
        List<Task> plan = taskService.findByDate(date);
        if(plan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plan);
    }

    /**
     * @param date Plan date
     * @param taskStatus Task status
     * @return List of tasks for given date with given status
     */
    @GetMapping("/{date}/{status}")
    public ResponseEntity<List<Task>> getDayPlan(@PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("status") String taskStatus) {
        List<Task> tasks = planService.getTasksWithStatus(
                getDayPlan(date).getBody(),
                taskStatus
        );

        if(isEmpty(tasks)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

}
