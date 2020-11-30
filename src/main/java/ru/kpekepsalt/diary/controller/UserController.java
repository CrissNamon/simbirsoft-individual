package ru.kpekepsalt.diary.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.service.AppUserService;

import java.util.concurrent.atomic.AtomicReference;

@Api(tags = "User")
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
    @Operation(summary = "Creates new user", responses = {
            @ApiResponse(responseCode = "200", description = "User created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppUser.class))}),
            @ApiResponse(responseCode = "226", description = "User with login already exists", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid user data", content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(value = "/", produces = {"application/json"})
    public ResponseEntity<AppUser> createUser(@Parameter(description = "User data") @RequestBody AppUserDto appUserDto){
        AtomicReference<ResponseEntity<AppUser>> responseEntityAtomicReference = new AtomicReference<>();
        appUserService.createUser(appUserDto,
                (user) -> responseEntityAtomicReference.set(ResponseEntity.ok(user)),
                () -> responseEntityAtomicReference.set(ResponseEntity.status(HttpStatus.IM_USED).build()),
                () -> responseEntityAtomicReference.set(ResponseEntity.badRequest().build())
        );
        return responseEntityAtomicReference.get();
    }

}
