package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.dto.AppUserDto;
import ru.kpekepsalt.diary.functional.Functional;
import ru.kpekepsalt.diary.functional.VoidParamActionFunctional;
import ru.kpekepsalt.diary.functional.VoidActionFunctional;
import ru.kpekepsalt.diary.mapper.AppUserMapper;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.model.Role;
import ru.kpekepsalt.diary.repository.UserRepository;
import ru.kpekepsalt.diary.service.AppUserService;

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
    public AppUser createUserObject(AppUserDto appUserDto) {
        AppUser appUser = AppUserMapper.INSTANCE.dtoToUser(appUserDto);
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
    public void createUser(AppUserDto appUserDto, VoidParamActionFunctional<AppUser> ok, VoidActionFunctional ifUserExists, VoidActionFunctional ifNoData) {
        if(isEmpty(appUserDto)) {
            ifNoData.action();
            return;
        }
        if(isUserExists(appUserDto.getLogin())) {
            ifUserExists.action();
            return;
        }
        AppUser appUser = createUserObject(appUserDto);
        save(appUser);
        ok.action(appUser);
    }

    @Override
    public void createUser(AppUserDto appUserDto) {
        createUser(
                appUserDto,
                Functional::consume,
                Functional::empty,
                Functional::empty
        );
    }

}
