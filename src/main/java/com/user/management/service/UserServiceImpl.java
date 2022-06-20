package com.user.management.service;

import com.user.management.entities.Role;
import com.user.management.entities.User;
import com.user.management.repositories.RoleRepository;
import com.user.management.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUserName(username);
        Role role = this.findRoleByRoleName(roleName);
        if(user.getRoles() !=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        // it is not necessary to save the user and the role because the method is transactional
        // userRepository.save(user);
        // roleRepository.save(role);
    }
}
