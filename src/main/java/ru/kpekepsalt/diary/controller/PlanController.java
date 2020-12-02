package ru.kpekepsalt.diary.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpekepsalt.diary.model.Plan;
import ru.kpekepsalt.diary.model.TaskStatus;
import ru.kpekepsalt.diary.service.PlanService;

@Api(tags = "Plan")
@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    /**
     * @return List of tasks for today
     */
    @ApiOperation(authorizations = {
                    @Authorization(value = "basicAuth")
            },
            value = "Get today plan"
    )
    @Operation(summary = "Get today plan", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Day plan created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Plan.class)
                            )
                    })
    })
    @GetMapping(value = "/", produces = "application/json")
    @PreAuthorize("hasAuthority('plan:get:now')")
    public ResponseEntity<Plan> getDayPlan() {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference =
                new AtomicReference<>();
        planService.getPlan(
                LocalDate.now(),
                null,
                plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param date Date for plan
     * @return List of tasks for given date
     */
    @ApiOperation(authorizations = {
               @Authorization(value = "basicAuth")
            },
            value = "Get plan for given date"
    )
    @Operation(summary = "Get plan for given date", responses = {
            @ApiResponse(responseCode = "200", description = "Plan created for given date",
                    content =
                            {
                                    @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Plan.class)
                                    )
                            }),
    })
    @GetMapping(value = "/{date}", produces = "application/json")
    @PreAuthorize("hasAuthority('plan:get:date')")
    public ResponseEntity<Plan> getDayPlan(
            @Parameter(name = "Date", description = "Date for plan", example = "2020-11-19")
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference =
                new AtomicReference<>();
        planService.getPlan(
                date,
                null,
                plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
        );
        return responseEntityAtomicReference.get();
    }

    /**
     * @param date       Plan date
     * @param taskStatus Task status
     * @return List of tasks for given date with given status
     */
    @ApiOperation(
            authorizations = {
                    @Authorization(value = "basicAuth")
            },
            value = "Get plan with tasks with given status for given date"
    )
    @Operation(summary = "Get plan with tasks with given status for given date",
            responses = {
                @ApiResponse(responseCode = "200", description = "Day plan created",
                    content = {
                        @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Plan.class)
                        )
                    }
                ),
                @ApiResponse(responseCode = "400", description = "Wrong status",
                    content = {
                        @Content(mediaType = "application/json")
                    }
                )
            }
    )
    @GetMapping(value = "/{date}/{status}", produces = "application/json")
    public ResponseEntity<Plan> getDayPlan(
            @Parameter(name = "Date", description = "Date for plan", example = "2020-11-19")
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(name = "Status", description = "Task status for plan", example = "OPEN")
            @PathVariable("status") String taskStatus) {
        AtomicReference<ResponseEntity<Plan>> responseEntityAtomicReference =
                new AtomicReference<>();
        try {
            planService.getPlan(
                    date,
                    TaskStatus.valueOf(taskStatus.toUpperCase()),
                    plan -> responseEntityAtomicReference.set(ResponseEntity.ok(plan))
            );
        } catch (IllegalArgumentException e) {
            responseEntityAtomicReference.set(ResponseEntity.badRequest().build());
        }
        return responseEntityAtomicReference.get();
    }
}
