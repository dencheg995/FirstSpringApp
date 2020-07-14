package ru.kuznetsov.stories.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import ru.kuznetsov.stories.models.User;

import java.util.List;

@Component
public interface UserService extends Validator{
    void save(User user);
    User findByLogin(String login);
    User findByEmail(String email);
    List<User> getUserList();

}
