package ru.job4j.accidents.service.user;

import ru.job4j.accidents.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
}
