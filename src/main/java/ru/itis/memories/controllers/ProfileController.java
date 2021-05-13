package ru.itis.memories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.memories.dto.UserDto;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;
import ru.itis.memories.security.details.UserDetailsImpl;
import ru.itis.memories.services.MemorieService;
import ru.itis.memories.services.UsersService;

import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    private MemorieService memorieService;

    @Autowired
    private UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", usersService.getUserByEmail(user.getUsername()));
        return "profile_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/settings")
    public String getSettings(@AuthenticationPrincipal UserDetailsImpl user, Model model){
        model.addAttribute("user", usersService.getUserByEmail(user.getUsername()));
        return "settings";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/settings")
    public String setSettings(@AuthenticationPrincipal UserDetailsImpl user, Model model,
                              @RequestParam(value = "firstName") String firstName,
                              @RequestParam(value = "lastName") String lastName,
                              @RequestParam(value = "file") MultipartFile file){

        usersService.updateUser(user.getId(), firstName, lastName, file);
        model.addAttribute("user", usersService.getUserByEmail(user.getUsername()));
        return "settings";
    }
}
