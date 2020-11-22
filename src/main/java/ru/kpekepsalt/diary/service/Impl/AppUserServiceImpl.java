package ru.kpekepsalt.diary.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.model.AppUser;
import ru.kpekepsalt.diary.repository.UserRepository;
import ru.kpekepsalt.diary.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Override
    public AppUser findByLogin(String login) {
        return userRepository.findByLogin(login).orElse(null);
    }
}
