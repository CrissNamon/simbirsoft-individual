package ru.kpekepsalt.diary.service;

import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.Task;

import java.time.LocalDate;
import java.util.List;

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

}
