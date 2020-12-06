package ru.kpekepsalt.diary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Task")
@Entity
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(example = "1")
    private Long id;

    @Column(name = "title")
    @ApiModelProperty(example = "Test task")
    private String title;

    @Column(name = "task_date")
    @ApiModelProperty(example = "2020-11-19")
    private LocalDate date;

    @Column(name = "start_time")
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    @JsonFormat(pattern = "HH::mm:ss")
    private LocalTime startTime;

    @Column(name = "end_time")
    @ApiModelProperty(example = "00:00:00", dataType = "java.sql.Date")
    @JsonFormat(pattern = "HH::mm:ss")
    private LocalTime endTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "status", example = "OPEN")
    private TaskStatus taskStatus;

    @Column(name = "user_id")
    @ApiModelProperty(hidden = true)
    private Long userId;

}
