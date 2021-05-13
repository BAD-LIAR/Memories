package ru.itis.memories.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.memories.dto.MemorieForm;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;
import ru.itis.memories.security.details.UserDetailsImpl;
import ru.itis.memories.services.MemorieAccessService;
import ru.itis.memories.services.MemorieService;
import ru.itis.memories.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class MemoriesController {

    @Autowired
    private MemorieService memorieService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MemorieAccessService memorieAccessService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/new_memorie")
    public String getCreateMemorie(Model model) {
        model.addAttribute("memorieForm", new MemorieForm());
        return "add_memorie";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/new_memorie")
    public String createMemorie(Principal principal, @RequestParam(value = "input") String input,
                                @RequestParam(value = "row") String[] rows) {


        Memorie memorie = new Memorie();
        List<String> list = new ArrayList<>();
        rows[0] = rows[0].substring(1);
        rows[rows.length - 1] = rows[rows.length - 1].substring(0, rows[rows.length - 1].length() - 1);
        for(String row: rows){
            if (!row.equals("")) {
                list.add(row.substring(1, row.length() - 1));
            }
        }

        usersService.addedToMemorie(principal.getName(), list);
        memorie.setText(input);
        memorie.setOwner(usersService.getUserByEmail(principal.getName()));
        memorieService.save(memorie);
        memorieAccessService.save(list, memorie);
        return "redirect:/profile";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my_memories")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl user, Model model) {
        model.addAttribute("user", user);
        List<Memorie> memorieList = usersService.getUserByEmail(user.getUsername()).getMemories();
        model.addAttribute("memoriesList", memorieList);
        return "my_memories";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/together")
    public String getTogetherMemories(@AuthenticationPrincipal UserDetailsImpl user, Model model){
        List<Memorie> memorieList = memorieService.getAllTogether(user.getId());
        model.addAttribute("memoriesList", memorieList);
        return "my_memories";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_user_to_memorie")
    public @ResponseBody
    String addUser(@RequestParam(value = "email") String email) {
        User user = usersService.getUserByEmail(email);
        if (usersService.findByEmail(email).isPresent()){
            String response = "<tr>\n" +
                    "                    <td contenteditable=\"true\">" + user.getId() + "</td>\n" +
                    "                    <td class=\"eml\" contenteditable=\"true\">" + user.getEmail() + "</td>\n" +
                    "                    <td contenteditable=\"true\">"+ user.getFirstName() + " " + user.getLastName() + "</td>\n" +
                    "                </tr>";
            return response;
        } else {
            return "<a id=\"not_found\" style=\"    background: red;\"> Not Found </a>";
        }
    }
}
