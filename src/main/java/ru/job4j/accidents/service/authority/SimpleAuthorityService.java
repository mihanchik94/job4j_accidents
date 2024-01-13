package ru.job4j.accidents.service.authority;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.authority.AuthorityRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAuthorityService implements AuthorityService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleAuthorityService.class);
    private AuthorityRepository authorityRepository;

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        Optional<Authority> result = Optional.ofNullable(authorityRepository.findByAuthority(authority));
        if (result.isEmpty()) {
            LOG.error("Ошибка при поиске прав пользователя. Права " + authority + " не существует");
        }
        return result;
    }
}