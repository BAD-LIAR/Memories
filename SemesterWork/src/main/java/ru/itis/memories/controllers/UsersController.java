package ru.itis.memories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.services.UsersService;


import javax.validation.Valid;
import java.util.Objects;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;






    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().anyMatch(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("userForm.ValidNames")) {
                    model.addAttribute("namesErrorMessage", error.getDefaultMessage());
                }
                return true;
            });
            model.addAttribute("userForm", form);
            return "signUp";
        }
        if (usersService.addUser(form)) {
            return "redirect:/signIn";
        } else {
            return "redirect:/signUp";
        }
    }




    @GetMapping("/signIn")
    public String getSignInPage() {
        return "login";
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("usersList", usersService.getAllUsers());
        return "users_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/ban")
    public @ResponseBody String banUser(@RequestParam(value = "recordToDelete") String recordToDelite) {
        usersService.banById(Long.parseLong(recordToDelite));
        return "baned";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/unban")
    public @ResponseBody String unBanUser(@RequestParam(value = "recordToDelete") String recordToDelite) {
        usersService.unBanById(Long.parseLong(recordToDelite));
        return "baned";
    }


}
