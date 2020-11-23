package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.TaskService;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * @param id Task id
     * @return Task information with given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long id) {
        AtomicReference<ResponseEntity<Task>> responseEntityAtomicReference = new AtomicReference<>();
        taskService.getTask(
                id,
                task -> responseEntityAtomicReference.set(ResponseEntity.ok(task)),
                () -> responseEntityAtomicReference.set(ResponseEntity.notFound().build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.status(HttpStatus.FORBIDDEN).build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.badRequest().build())
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param taskDto Task object
     * @return HTTP 201 if task created
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('task:add')")
    public ResponseEntity<Task> addTask(@RequestBody TaskDto taskDto) {
        AtomicReference<ResponseEntity<Task>> responseEntityAtomicReference = new AtomicReference<>();
        taskService.addTask(
                taskDto,
                task -> responseEntityAtomicReference.set(ResponseEntity.ok(task)),
                () -> responseEntityAtomicReference.set(ResponseEntity.badRequest().build())
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param id Task id
     * @return HTTP 200 if task deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id) {
        AtomicReference<ResponseEntity<Task>> responseEntityAtomicReference = new AtomicReference<>();
        taskService.removeTask(
                id,
                () -> responseEntityAtomicReference.set(ResponseEntity.ok().build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.notFound().build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.status(HttpStatus.FORBIDDEN).build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.badRequest().build())
        );
        return responseEntityAtomicReference.get();
    }

}
