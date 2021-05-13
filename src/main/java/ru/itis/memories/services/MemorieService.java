package ru.itis.memories.services;


import ru.itis.memories.dto.MemorieForm;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;

import java.util.List;

public interface MemorieService {

    List<Memorie> getAll();

    Memorie getById(Long id);

    void save(Memorie memorie);

    List<Memorie> getAllByOwner(User user);

    List<Memorie> getAllTogether(Long id);
}
