package ru.kpekepsalt.diary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.kpekepsalt.diary.model.Role;

@ApiModel("AppUserDto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppUserDto {

    @ApiModelProperty(example = "Kpekep")
    private String login;

    @ApiModelProperty(example = "123456abc")
    private String password;

    @ApiModelProperty(hidden = true)
    private Role role;

}
