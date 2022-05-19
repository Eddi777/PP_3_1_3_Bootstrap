package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("usersList", userService.findAll());
        model.addAttribute("userActive", user);
        model.addAttribute("userAdd", new User());
        model.addAttribute("userUpdate", new User());

        return "admin";
    }

    @GetMapping(value = "/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/user-update")
    public String updateUser(User user) {
        user.setActive(true);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/user-create")
    public String createUser(User user ) {
        User userFromBD = userService.getUserByUsername(user.getUsername());
        if (userFromBD == null) {
            user.setActive(true);
            userService.saveUser(user);
        }
        return "redirect:/admin";
    }
}
