package ru.itis.memories.services;

import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.MemorieAccess;

import java.util.List;

public interface MemorieAccessService {



    void save(List<String> emails, Memorie memorie);

}
