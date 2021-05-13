package ru.itis.memories.services;


import org.springframework.stereotype.Service;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.MemorieAccess;
import ru.itis.memories.repositories.MemorieAccessRepository;
import ru.itis.memories.repositories.MemoriesRepository;
import ru.itis.memories.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemorieAccessServiceImpl implements MemorieAccessService {


    private final MemorieAccessRepository memorieAccessRepository;
    private final UsersRepository usersRepository;
    private final MemoriesRepository memoriesRepository;

    public MemorieAccessServiceImpl(MemorieAccessRepository memorieAccessRepository, UsersRepository usersRepository, MemoriesRepository memoriesRepository) {
        this.memorieAccessRepository = memorieAccessRepository;
        this.usersRepository = usersRepository;
        this.memoriesRepository = memoriesRepository;
    }


    @Override
    public void save(List<String> emails, Memorie memorie) {
        List<MemorieAccess> memorieAccessList = new ArrayList<>();
        for (String email: emails){
            MemorieAccess memorieAccess = new MemorieAccess();
            memorieAccess.setParticipant(usersRepository.getUserByEmail(email));
            memorieAccess.setMemorie(memorie);
            memorieAccessList.add(memorieAccess);

        }
        memorieAccessRepository.saveAll(memorieAccessList);
    }
}
