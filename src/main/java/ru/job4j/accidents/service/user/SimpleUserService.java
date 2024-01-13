package ru.job4j.accidents.service.user;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.user.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    @Override
    public Optional<User> save(User user) {
        try {
            userRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            LOG.error("Произошла ошибка при сохранении пользователя. Возможно такой пользователь уже существует " + e);
        }
        return Optional.empty();
    }
}