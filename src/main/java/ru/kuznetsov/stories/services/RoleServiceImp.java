package ru.kuznetsov.stories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kuznetsov.stories.dao.RoleDao;
import ru.kuznetsov.stories.models.Role;

@Service
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao){
        this.roleDao = roleDao;
    }
    @Override
    public Role getRoleById(Long id) {
        return roleDao.getOne(id);
    }
}
