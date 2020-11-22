package ru.kpekepsalt.diary.service;

import ru.kpekepsalt.diary.model.AppUser;

public interface AppUserService {

    void save(AppUser appUser);
    AppUser findByLogin(String login);
}
