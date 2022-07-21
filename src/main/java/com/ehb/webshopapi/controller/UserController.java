package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.Role;
import com.ehb.webshopapi.models.User;
import com.ehb.webshopapi.services.UserSevice;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class UserController {

    private final UserSevice userSevice;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userSevice.getusers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userSevice.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userSevice.saveRole(role));
    }

    @PostMapping("/user/addrole")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userSevice.addRoleToUser(form.username, form.roleName);
        return ResponseEntity.ok().build();
    }

    @Data
    class RoleToUserForm {
        private String username;
        private String roleName;
    }

}
