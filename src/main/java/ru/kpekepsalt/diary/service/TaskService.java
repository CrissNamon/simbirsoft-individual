package ru.kpekepsalt.diary.service;

import java.time.LocalDate;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.functional.VoidActionFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.Task;

public interface TaskService{

    /**
     * Save task in DB from given Task DTO
     * @param taskDto Task DTO
     */
    boolean save(TaskDto taskDto);

    /**
     * Save task in DB from given Task object
     * @param task Save Task object
     */
    boolean save(Task task);

    /**
     * Delete task with given id from DB
     * @param id Task id
     */
    void delete(Long id);

    /**
     * Find task with given id in DB
     * @param id Task id
     * @return Task with given id
     */
    Task findById(Long id);

    /**
     * @param date Date for searching
     * @return List of tasks with given date
     */
    Plan findByDate(LocalDate date);

    Plan findByUserIdAndDate(Long userId, LocalDate date);

    Plan findByUserId(Long userId);

    void getTask(Long id, VoidParamActionFunctional<Task> ok, VoidActionFunctional ifNotFound,
                 VoidActionFunctional ifForbidden, VoidActionFunctional ifNoData);

    void addTask(TaskDto taskDto, VoidParamActionFunctional<Task> ok, VoidActionFunctional ifNoData);

    void removeTask(Long id, VoidActionFunctional ok, VoidActionFunctional ifNotFound,
                    VoidActionFunctional ifForbidden, VoidActionFunctional ifNoData);

    Task getTask(Long id);
    Task addTask(TaskDto taskDto);
    void removeTask(Long id);
}
