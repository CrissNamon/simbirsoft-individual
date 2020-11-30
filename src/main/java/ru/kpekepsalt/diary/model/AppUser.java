package ru.kpekepsalt.diary.model;

import org.springframework.stereotype.Component;
import ru.kpekepsalt.diary.dto.AppUserDto;

import javax.persistence.*;

@Entity(name = "appuser")
@Component
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public AppUser() {}

    public AppUser(AppUserDto appUserDto) {
        this.login = appUserDto.getLogin();
        this.password = appUserDto.getPassword();
        this.role = appUserDto.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
