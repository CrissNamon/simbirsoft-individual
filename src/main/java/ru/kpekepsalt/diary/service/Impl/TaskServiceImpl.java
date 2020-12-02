package ru.kpekepsalt.diary.service.Impl;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.functional.Functional;
import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.functional.VoidActionFunctional;
import ru.kpekepsalt.diary.mapper.TaskMapper;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.repository.TaskRepository;
import ru.kpekepsalt.diary.service.TaskService;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public boolean save(TaskDto taskDto) {
        Task task = TaskMapper.INSTANCE.dtoToTask(taskDto);
        return isEmpty(
                taskRepository.save(task)
        );
    }

    @Override
    public boolean save(Task task) {
        return isEmpty(
                taskRepository.save(task)
        );
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Plan findByDate(LocalDate date) {
        return new Plan(taskRepository.findByDate(date));
    }

    @Override
    public Plan findByUserIdAndDate(Long userId, LocalDate date) {
        return new Plan(taskRepository.findByUserIdAndDate(userId, date));
    }

    @Override
    public Plan findByUserId(Long userId) {
        return new Plan(taskRepository.findByUserId(userId));
    }

    @Override
    public void getTask(Long id, VoidParamActionFunctional<Task> ok,
                        VoidActionFunctional ifNotFound, VoidActionFunctional ifForbidden,
                        VoidActionFunctional ifNoData) {
        if(isEmpty(id)) {
            ifNoData.action();
            return;
        }
        Task task = findById(id);
        if(isEmpty(task)) {
            ifNotFound.action();
            return;
        }
        if(!task.getUserId().equals(userDetailsService.getUserid())
                && !userDetailsService.hasAuthority("task:get")) {
            ifForbidden.action();
            return;
        }
        ok.action(task);
    }

    @Override
    public void addTask(TaskDto taskDto, VoidParamActionFunctional<Task> ok, VoidActionFunctional ifNoData) {
        if(isEmpty(taskDto)) {
            ifNoData.action();
            return;
        }
        Task task = TaskMapper.INSTANCE.dtoToTask(taskDto);
        task.setUserId(userDetailsService.getUserid());
        save(task);
        ok.action(task);
    }

    @Override
    public void removeTask(Long id, VoidActionFunctional ok, VoidActionFunctional ifNotFound,
                           VoidActionFunctional ifForbidden, VoidActionFunctional ifNoData) {
        if(isEmpty(id)) {
            ifNoData.action();
            return;
        }
        Task task = findById(id);
        if(!userDetailsService.hasAuthority("task:remove")
                && !userDetailsService.getUserid().equals(task.getUserId())) {
            ifForbidden.action();
            return;
        }
        if(isEmpty(task)) {
            ifNotFound.action();
            return;
        }
        delete(id);
        ok.action();
    }

    @Override
    public Task getTask(Long id) {
        AtomicReference<Task> atomicReference = new AtomicReference<>();
        getTask(
                id,
                atomicReference::set,
                Functional::empty,
                Functional::empty,
                Functional::empty
        );
        return atomicReference.get();
    }

    @Override
    public Task addTask(TaskDto taskDto) {
        AtomicReference<Task> atomicReference = new AtomicReference<>();
        addTask(
                taskDto,
                atomicReference::set,
                Functional::empty
        );
        return atomicReference.get();
    }

    @Override
    public void removeTask(Long id) {
        removeTask(
                id,
                Functional::empty,
                Functional::empty,
                Functional::empty,
                Functional::empty
        );
    }

}
