package ru.kpekepsalt.diary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.service.AppUserService;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    /**
     * Creates new user with given login and password
     * @param appUserDto AppUser object
     * @return HTTP 200 if user created
     */
    @PostMapping("/")
    public ResponseEntity<AppUser> createUser(@RequestBody AppUserDto appUserDto){
        AtomicReference<ResponseEntity<AppUser>> responseEntityAtomicReference = new AtomicReference<>();
        appUserService.createUser(appUserDto,
                (user) -> responseEntityAtomicReference.set(ResponseEntity.ok(user)),
                () -> responseEntityAtomicReference.set(ResponseEntity.status(HttpStatus.IM_USED).build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.badRequest().build())
        );
        return responseEntityAtomicReference.get();
    }

}
