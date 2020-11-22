package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.model.Role;
import ru.kpekepsalt.diary.service.AppUserService;
import ru.kpekepsalt.diary.service.Impl.UserDetailsServiceImpl;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates new user with given login and password
     * @param appUserDto AppUser object
     * @return HTTP 200 if user created
     */
    @PostMapping("/")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUserDto appUserDto){
        if(isEmpty(appUserDto)) {
            return ResponseEntity.badRequest().build();
        }
        AppUser appUser = appUserService.findByLogin(appUserDto.getLogin());
        if(!isEmpty(appUser)) {
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }
        appUser = new AppUser(appUserDto);
        appUser.setPassword(
                passwordEncoder.encode(appUser.getPassword())
        );
        appUser.setRole(Role.AUTHOR);
        appUserService.save(appUser);
        return ResponseEntity.ok().build();
    }

}
