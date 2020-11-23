package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.functional.ParamResponseFunctional;
import ru.kpekepsalt.diary.functional.ResponseFunctional;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.model.Role;
import ru.kpekepsalt.diary.repository.UserRepository;
import ru.kpekepsalt.diary.service.AppUserService;
import ru.kpekepsalt.diary.service.ResponseService;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public AppUser findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }

    @Override
    public AppUser createUser(AppUserDto appUserDto) {
        AppUser appUser = new AppUser(appUserDto);
        appUser.setPassword(
                passwordEncoder.encode(appUser.getPassword())
        );
        appUser.setRole(Role.AUTHOR);
        return appUser;
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isUserExists(String login) {
        AppUser appUser = findByLogin(login);
        return !isEmpty(appUser);
    }

    @Override
    public boolean isUserExists(Long id) {
        AppUser appUser = findById(id);
        return !isEmpty(appUser);
    }

    @Override
    public ResponseEntity<AppUser> createUser(AppUserDto appUserDto, ResponseFunctional<AppUser> ok, ResponseFunctional<AppUser> ifUserExists, ResponseFunctional<AppUser> ifNoData) {
        if(isEmpty(appUserDto)) {
            return ifNoData.action();
        }
        if(isUserExists(appUserDto.getLogin())) {
            return ifUserExists.action();
        }
        AppUser appUser = createUser(appUserDto);
        save(appUser);
        return ok.action();
    }

    @Override
    public ResponseEntity<AppUser> createUser(AppUserDto appUserDto, ParamResponseFunctional<AppUser> ok, ResponseFunctional<AppUser> ifUserExists, ResponseFunctional<AppUser> ifNoData) {
        if(isEmpty(appUserDto)) {
            return ifNoData.action();
        }
        if(isUserExists(appUserDto.getLogin())) {
            return ifUserExists.action();
        }
        AppUser appUser = createUser(appUserDto);
        save(appUser);
        return ok.action(appUser);
    }

}
