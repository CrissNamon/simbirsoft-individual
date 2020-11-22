package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.Impl.UserDetailsServiceImpl;
import ru.kpekepsalt.diary.service.TaskService;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
        Long userId = userDetailsService.getUserid();;
        if(isEmpty(task)) {
            return ResponseEntity.notFound().build();
        }
        if(!task.getUserId().equals(userId) && !userDetailsService.hasAuthority("task:get")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
    @PreAuthorize("hasAuthority('task:add')")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {
        if(isEmpty(taskDto)) {
            return ResponseEntity.badRequest().build();
        }
        Task task = new Task(taskDto);
        task.setUserId(userDetailsService.getUserid());
        taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
        if(!userDetailsService.hasAuthority("task:remove") && !userDetailsService.getUserid().equals(task.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if(isEmpty(task)) {
            return ResponseEntity.notFound().build();
        }
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

}
