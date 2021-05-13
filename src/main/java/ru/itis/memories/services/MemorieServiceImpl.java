package ru.itis.memories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.memories.dto.MemorieForm;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;
import ru.itis.memories.repositories.MemoriesRepository;

import java.util.List;

@Service
public class MemorieServiceImpl implements MemorieService {

    private final MemoriesRepository memoriesRepository;

    public MemorieServiceImpl(MemoriesRepository memoriesRepository) {
        this.memoriesRepository = memoriesRepository;
    }

    @Override
    public List<Memorie> getAll() {
        return memoriesRepository.findAll();
    }

    @Override
    public Memorie getById(Long id) {
        return memoriesRepository.getById(id);
    }

    @Override
    public void save(Memorie memorie) {
        memoriesRepository.save(memorie);
    }

    @Override
    public List<Memorie> getAllByOwner(User user) {
        return memoriesRepository.getAllByOwner(user);
    }

    @Override
    public List<Memorie> getAllTogether(Long id) {
        return memoriesRepository.getAllTogether(id);
    }
}
