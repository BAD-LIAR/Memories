package ru.itis.memories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.MemorieAccess;
import ru.itis.memories.models.User;
import ru.itis.memories.repositories.MemoriesRepository;
import ru.itis.memories.repositories.UsersRepository;

import java.util.List;

@Service
public class MemorieServiceImpl implements MemorieService {

    private final MemoriesRepository memoriesRepository;

    @Autowired
    private UsersRepository usersRepository;

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
        return memoriesRepository.getAllByOwnerAndIsDeleted(user, false);
    }

    @Override
    public List<Memorie> getAllTogether(Long id) {
        return memoriesRepository.getAllTogether(id);
    }

    @Override
    public void deleteById(Long id) {
        Memorie memorie = memoriesRepository.getById(id);
        User user = memorie.getOwner();
        user.setMemoriesCount(user.getMemoriesCount() - 1);
        for (MemorieAccess memorieAccess: memorie.getParticipantMemories()){
            User user1 = memorieAccess.getParticipant();
            user1.setTogetherMemoriesCount(user1.getTogetherMemoriesCount() - 1);
        }
        usersRepository.save(user);
        memoriesRepository.setIsDeletedTrue(id);
    }

    @Override
    public List<User> getAllPartipient(Long id) {
        List<User> list = memoriesRepository.getPartipients(id);
        return memoriesRepository.getPartipients(id);
    }
}
