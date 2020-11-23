package ru.kpekepsalt.diary.service;

import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.functional.VoidActionFunctional;
import ru.kpekepsalt.diary.model.AppUser;

public interface AppUserService {
    void save(AppUser appUser);
    AppUser findByLogin(String login);
    AppUser createUserObject(AppUserDto appUserDto);
    AppUser findById(Long id);
    boolean isUserExists(String login);
    boolean isUserExists(Long id);
    void createUser(AppUserDto appUserDto, VoidParamActionFunctional<AppUser> ok, VoidActionFunctional ifUserExists, VoidActionFunctional ifNoData);
    void createUser(AppUserDto appUserDto);
}
