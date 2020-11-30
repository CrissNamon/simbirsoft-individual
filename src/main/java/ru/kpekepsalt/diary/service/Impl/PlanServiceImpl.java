package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;
import ru.kpekepsalt.diary.service.TaskService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void getPlan(LocalDate date, TaskStatus taskStatus, VoidParamActionFunctional<Plan> ok) {
        Optional<LocalDate> optionalLocalDate = Optional.of(date);
        LocalDate localDate = optionalLocalDate.orElse(LocalDate.now());
        Plan plan = taskService.findByUserIdAndDate(
                userDetailsService.getUserid(),
                localDate
        );
        if(!isEmpty(taskStatus)) {
            plan = filterByStatus(plan, taskStatus);
        }
        ok.action(plan);
    }

    @Override
    public Plan getPlan(LocalDate date, TaskStatus status) {
        AtomicReference<Plan> atomicReference = new AtomicReference<>();
        getPlan(
                date,
                status,
                atomicReference::set
        );
        return atomicReference.get();
    }

    @Override
    public Plan getPlan(LocalDate date) {
        AtomicReference<Plan> atomicReference = new AtomicReference<>();
        getPlan(
                date,
                null,
                atomicReference::set
        );
        return atomicReference.get();
    }

    public Plan filterByStatus(Plan plan, TaskStatus taskStatus) {
        List<Task> filtered = plan.getPlan()
                .stream()
                .filter(task
                        ->
                        task.getTaskStatus()
                                .equals(taskStatus)
                )
                .collect(
                        Collectors.toList()
                );
        plan.setPlan(filtered);
        return plan;
    }

}
