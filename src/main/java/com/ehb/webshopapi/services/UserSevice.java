package com.ehb.webshopapi.services;

import com.ehb.webshopapi.models.Role;
import com.ehb.webshopapi.models.User;

import java.util.List;

public interface UserSevice {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getusers();
}
