package ru.itis.memories.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.security.details.UserDetailsImpl;
import ru.itis.memories.services.MemorieService;
import ru.itis.memories.services.UsersService;

import java.util.List;


@Controller
public class ProfileController {

    @Autowired
    MemorieService memorieService;

    @Autowired
    UsersService usersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        List<Memorie> memorieList = usersService.getUserByEmail(user.getUsername()).getMemories();
        model.addAttribute("memoriesList", memorieList);
        return "profile_page";
    }
}
