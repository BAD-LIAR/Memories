package ru.itis.memories.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.memories.dto.MemorieForm;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.models.User;
import ru.itis.memories.security.details.UserDetailsImpl;
import ru.itis.memories.services.MemorieService;
import ru.itis.memories.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
public class MemoriesController {

    @Autowired
    private MemorieService memorieService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/new_memorie")
    public String getCreateMemorie(Model model) {
        model.addAttribute("memorieForm", new MemorieForm());
        return "add_memorie";
    }


    @PostMapping("/new_memorie")
    public String createMemorie(MemorieForm form, Principal principal) {


        form.setOwner(usersService.getUserByEmail(principal.getName()));
        memorieService.save(form);
        return "redirect:/profile";

    }
}
