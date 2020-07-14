package ru.kuznetsov.stories.services;

import org.springframework.stereotype.Component;
import ru.kuznetsov.stories.models.Role;

@Component
public interface RoleService {
    Role getRoleById(Long id);
}
