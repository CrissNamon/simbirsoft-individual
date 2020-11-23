package ru.kpekepsalt.diary.service;

import org.springframework.http.ResponseEntity;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.functional.ParamResponseFunctional;
import ru.kpekepsalt.diary.functional.ResponseFunctional;
import ru.kpekepsalt.diary.model.AppUser;

public interface AppUserService {

    void save(AppUser appUser);
    AppUser findByLogin(String login);
    AppUser createUser(AppUserDto appUserDto);
    AppUser findById(Long id);
    boolean isUserExists(String login);
    boolean isUserExists(Long id);
    ResponseEntity<AppUser> createUser(AppUserDto appUserDto, ResponseFunctional<AppUser> ok, ResponseFunctional<AppUser> ifUserExists, ResponseFunctional<AppUser> ifNoData);
    ResponseEntity<AppUser> createUser(AppUserDto appUserDto, ParamResponseFunctional<AppUser> ok, ResponseFunctional<AppUser> ifUserExists, ResponseFunctional<AppUser> ifNoData);
}
