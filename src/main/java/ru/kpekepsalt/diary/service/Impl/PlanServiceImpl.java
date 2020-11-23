package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.functional.ParamResponseFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;
import ru.kpekepsalt.diary.service.TaskService;

import java.time.LocalDate;
import java.util.Optional;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public ResponseEntity<Plan> getPlan(LocalDate date, TaskStatus taskStatus, ParamResponseFunctional<Plan> ok) {
        Optional<LocalDate> optionalLocalDate = Optional.of(date);
        Optional<TaskStatus> optionalTaskStatus = Optional.ofNullable(taskStatus);

        LocalDate localDate = optionalLocalDate.orElse(LocalDate.now());
        Plan plan = taskService.findByUserIdAndDate(
                userDetailsService.getUserid(),
                localDate
        );
        TaskStatus status = optionalTaskStatus.orElse(null);
        if(!isEmpty(status)) {
            plan.filterByStatus(status);
        }
        return ok.action(plan);
    }
}
