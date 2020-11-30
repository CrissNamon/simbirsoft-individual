package ru.kpekepsalt.diary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@ApiModel("Plan")
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @ApiModelProperty(dataType = "object")
    @Getter
    @Setter
    private List<Task> plan;

    public boolean isEmpty() {
        return plan.isEmpty();
    }

}
