package ru.kpekepsalt.diary.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "appuser")
@Component
@ApiModel("AppUser")
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(name = "id", example = "1")
    @Getter
    @Setter
    private Long id;

    @Column(name = "login")
    @ApiModelProperty(name = "login", example = "Kpekep")
    @Getter
    @Setter
    private String login;

    @Column(name = "password")
    @ApiModelProperty(name = "password", example = "$2a$12$wHgf1MgL4e5J50XZD1V0G.qiwdORLVPx5kP9WMl//GZ2BUwj5DD4S")
    @Getter
    @Setter
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(name = "role", example = "AUTHOR")
    @Getter
    @Setter
    private Role role;
}
