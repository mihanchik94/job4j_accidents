package ru.job4j.accidents.service.authority;

import ru.job4j.accidents.model.Authority;

import java.util.Optional;

public interface AuthorityService {
    Optional<Authority> findByAuthority(String authority);
}