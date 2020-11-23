package ru.kpekepsalt.diary.service;

import org.springframework.http.ResponseEntity;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.functional.ParamResponseFunctional;
import ru.kpekepsalt.diary.functional.ResponseFunctional;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.Task;

import java.time.LocalDate;

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

    ResponseEntity<Task> getTask(Long id, ParamResponseFunctional<Task> ok, ResponseFunctional<Task> ifNotFound,
                                 ResponseFunctional<Task> ifForbidden, ResponseFunctional<Task> ifNoData);

    ResponseEntity<Task> addTask(TaskDto taskDto, ParamResponseFunctional<Task> ok, ResponseFunctional<Task> ifNoData);

    ResponseEntity<Task> removeTask(Long id, ResponseFunctional<Task> ok, ResponseFunctional<Task> ifNotFound,
                                    ResponseFunctional<Task> ifForbidden, ResponseFunctional<Task> ifNoData);
}
