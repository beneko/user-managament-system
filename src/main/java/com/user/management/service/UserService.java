package com.user.management.service;

import com.user.management.entities.Role;
import com.user.management.entities.User;

public interface UserService {
    User addUser(User user);
    Role addRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username , String roleName);
}
