package ru.kpekepsalt.diary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.TaskDto;
import ru.kpekepsalt.diary.model.Task;
import ru.kpekepsalt.diary.service.TaskService;

import java.util.concurrent.atomic.AtomicReference;

@Api(tags = "Task")
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * @param id Task id
     * @return Task information with given id
     */
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "Get task information")
    @Operation(summary = "Get task information", responses = {
            @ApiResponse(responseCode = "200", description = "Task found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Wrong task id", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "User have no permissions to see this task", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Task with given id not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Task> getTask(@Parameter(description = "Task id", example = "1") @PathVariable("id") Long id) {
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
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "Add task")
    @Operation(summary = "Add task", responses = {
            @ApiResponse(responseCode = "200", description = "Task added", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Wrong task information", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(value = "/add", produces = "application/json")
    @PreAuthorize("hasAuthority('task:add')")
    public ResponseEntity<Task> addTask(@Parameter(description = "Task information") @RequestBody TaskDto taskDto) {
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
    @ApiOperation(authorizations = {@Authorization(value = "basicAuth")}, value = "Delete task")
    @Operation(summary = "Delete task with given id", responses = {
            @ApiResponse(responseCode = "200", description = "Task deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Wrong task id", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Task> deleteTask(@Parameter(description = "Task id", example = "1") @PathVariable("id") Long id) {
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
