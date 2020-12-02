package ru.kpekepsalt.diary.model;

import javax.persistence.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity(name = "appuser")
@Component
@ApiModel("AppUser")
@NoArgsConstructor
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "id", example = "1")
    private Long id;

    @Column(name = "login")
    @ApiModelProperty(name = "login", example = "Kpekep")

    private String login;

    @Column(name = "password")
    @ApiModelProperty(name = "password", example = "$2a$12$wHgf1MgL4e5J50XZD1V0G.qiwdORLVPx5kP9WMl//GZ2BUwj5DD4S")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(name = "role", example = "AUTHOR")
    private Role role;
}
