package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kuznetsov.stories.dao.RoleDao;
import ru.kuznetsov.stories.dao.UserDao;
import ru.kuznetsov.stories.models.Role;
import ru.kuznetsov.stories.models.User;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService{

    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date());
        user.getRoles().add(roleService.getRoleById(1L));
        userDao.save(user);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> getUserList() {
        return userDao.findAll();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if(!Pattern.matches("^[a-zA-Z0-9_]+$",user.getLogin())){
            errors.rejectValue("login","","Логин может " +
                    "содержать только буквы латинского или русского алфавита, цифры и знак подчёркивания");
        }
        if(findByLogin(user.getLogin()) != null){
            errors.rejectValue("login","","Этот логин уже занят");
        }
        if(findByEmail(user.getEmail()) != null){
            errors.rejectValue("email","","Этот email уже занят");
        }
        if(!user.getPassword().equals(user.getConfirmPassword())){
            errors.rejectValue("password","","Пароли не совпадают");
        }
    }
}
