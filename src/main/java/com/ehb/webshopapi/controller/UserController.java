package com.ehb.webshopapi.controller;

import com.ehb.webshopapi.models.User;
import com.ehb.webshopapi.services.UserSevice;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserSevice userSevice;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userSevice.getusers());
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user){
        log.info(user.getUsername());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        try{

            return ResponseEntity.created(uri).body(userSevice.saveUser(user));
        } catch (Exception exception){
            Map<String, String> error = new HashMap<>();
            Throwable t;
            for (t = exception.getCause(); t != null; t = t.getCause()) {
                error.put("error", t.getMessage().split("for")[0]);
            }


            return ResponseEntity.badRequest().body(error);
        }
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
