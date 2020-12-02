package ru.kpekepsalt.diary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

import lombok.*;

@ApiModel("Plan")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plan {

    @ApiModelProperty(dataType = "object")
    private List<Task> plan;

    public boolean isEmpty() {
        return plan.isEmpty();
    }

}
