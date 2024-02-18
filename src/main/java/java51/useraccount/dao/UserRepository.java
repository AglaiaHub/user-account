package java51.useraccount.dao;

import java51.useraccount.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByLogin(String login);
}
