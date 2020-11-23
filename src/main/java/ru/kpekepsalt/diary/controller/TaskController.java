package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.ResponseService;
import ru.kpekepsalt.diary.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ResponseService<Task> responseService;

    /**
     * @param id Task id
     * @return Task information with given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        return taskService.getTask(id,
                task -> responseService.ok(task),
                () -> responseService.notFound(),
                () -> responseService.forbidden(),
                () -> responseService.badRequest()
        );
    }

    /**
     * @param taskDto Task object
     * @return HTTP 201 if task created
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('task:add')")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        return taskService.addTask(
                taskDto,
                task -> responseService.ok(task),
                () -> responseService.badRequest()
        );
    }

    /**
     * @param id Task id
     * @return HTTP 200 if task deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        return taskService.removeTask(
                id,
                () -> responseService.ok(),
                () -> responseService.notFound(),
                () -> responseService.forbidden(),
                () -> responseService.badRequest()
        );
    }

}
