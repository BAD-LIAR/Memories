package ru.itis.memories.services;



import org.springframework.web.multipart.MultipartFile;
import ru.itis.memories.dto.UserDto;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersService {
    List<UserDto> getAllUsers();
    boolean addUser(UserForm userDto);

    User getUser(Long userId);
    public boolean containsUser(String email);
    Optional<User> findByEmail(String email);
    void banById(Long id);


    void unBanById(Long parseLong);

    User getUserByEmail(String email);


    void updateUser(Long id, String firstName, String lastName, MultipartFile file);



    void addedToMemorie(String owner, List<String> strings);

    void deletedFromMemorie(Memorie memorie);
}
