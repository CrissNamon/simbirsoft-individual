package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.service.AppUserService;
import ru.kpekepsalt.diary.service.ResponseService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ResponseService<AppUser> responseService;

    /**
     * Creates new user with given login and password
     * @param appUserDto AppUser object
     * @return HTTP 200 if user created
     */
    @PostMapping("/")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUserDto appUserDto){
        return appUserService.createUser(appUserDto,
                (user) -> responseService.ok(user),
                () -> responseService.status(HttpStatus.IM_USED),
                () -> responseService.badRequest()
        );
    }

}
