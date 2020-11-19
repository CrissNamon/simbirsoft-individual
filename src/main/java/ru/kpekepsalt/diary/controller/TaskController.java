package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.TaskService;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

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
        if(isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskService.findById(id);
        if(isEmpty(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    /**
     * @param taskDto Task object
     * @return HTTP 201 if task created
     */
    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {
        if(isEmpty(taskDto)) {
            return ResponseEntity.badRequest().build();
        }

        if(taskService.save(taskDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @param id Task id
     * @return HTTP 200 if task deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        if(isEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        Task task = taskService.findById(id);
        if(isEmpty(task)) {
            return ResponseEntity.notFound().build();
        }
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

}
