package umc.spring.service.UserService;

import umc.spring.domain.User;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUserById(Long id);
}
