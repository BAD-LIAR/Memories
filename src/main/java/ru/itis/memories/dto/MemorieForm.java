package ru.itis.memories.dto;


import lombok.Data;
import ru.itis.memories.models.User;

import java.util.List;

@Data
public class MemorieForm {

    private Long id;
    private User owner;
    private String text;
    private List<User> participant;
}
