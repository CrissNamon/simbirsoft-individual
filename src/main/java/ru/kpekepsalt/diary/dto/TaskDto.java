package ru.kpekepsalt.diary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.kpekepsalt.diary.model.TaskStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@ApiModel("TaskDto")
@Data
public class TaskDto {

    /**
     * Task title
     */
    @ApiModelProperty(example = "Test task")
    private String title;
    /**
     * Task date
     */
    @ApiModelProperty(example = "2020-11-19")
    private LocalDate date;
    /**
     * Task start time
     */
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    private LocalTime startTime;
    /**
     * Task end time
     */
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    private LocalTime endTime;
    /**
     * Task status
     */
    @ApiModelProperty(name = "status", example = "OPEN")
    private Optional<TaskStatus> taskStatus;

    /**
     * @return Task status
     */
    public TaskStatus getTaskStatus() {
        return taskStatus.orElse(TaskStatus.OPEN);
    }

    /**
     * @param taskStatus Task status
     */
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = Optional.of(taskStatus);
    }
}
