package com.github.andygo298.testTask.controller;

import com.github.andygo298.testTask.controller.request.EditUserRq;
import com.github.andygo298.testTask.model.User;
import com.github.andygo298.testTask.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String toGreeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/user")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<User> users;

        if (filter != null && !filter.isEmpty()) {
            users = Collections.singletonList(userService.findByName(filter));
        } else {
            users = userService.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "user";
    }

    @GetMapping("/adminpage")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String toAdminPage(Model model) {
        List<User> all = userService.findAll();
        model.addAttribute("users", all);
        return "adminpage";
    }

    @GetMapping("/user/new")
    public String toAddUser() {
        return "createuser";
    }

    @PostMapping("/user/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "createuser";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "createuser";
        }

        return "redirect:/adminpage";
    }

    @GetMapping("/user/{id}/edit")
    public String toEditUser(@PathVariable(name = "id") Integer userId, Model model) {
        Optional<User> userById = userService.findById(userId);
        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
        } else {
            model.addAttribute("errorUser", "User not found!");
        }
        return "editUser";
    }

    @PostMapping("/user/{id}/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@Valid EditUserRq editUser,
                           BindingResult bindingResult,
                           Model model,
                           @PathVariable(name = "id") Integer userId) {

        if (bindingResult.hasErrors()) {
            editUser.setId(userId);
            Map<String, String> editErrors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(editErrors);
            model.addAttribute("user", editUser);
            return "edituser";
        }

        if (!userService.editUser(editUser, userId)) {
            model.addAttribute("usernameError", "User not found!");
            return "edituser";
        }
        return "redirect:/adminpage";
    }
}
