package ru.kpekepsalt.diary.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kpekepsalt.diary.model.Role;

@ApiModel("AppUserDto")
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {

    @ApiModelProperty(example = "Kpekep")
    @Getter
    @Setter
    private String login;

    @ApiModelProperty(example = "123456abc")
    @Getter
    @Setter
    private String password;

    @ApiModelProperty(hidden = true)
    @Getter
    @Setter
    private Role role;

}
