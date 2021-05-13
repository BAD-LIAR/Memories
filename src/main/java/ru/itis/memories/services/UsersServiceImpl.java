package ru.itis.memories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.memories.dto.UserDto;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.models.User;
import ru.itis.memories.repositories.UsersRepository;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;





@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }



    @Override
    public boolean addUser(UserForm userDto) {
        User newUser = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .avatar("/styles/img/avatars/profile_photo.jpg")
                .memoriesCount(0)
                .togetherMemoriesCount(0)
                .build();
        if (!containsUser(newUser.getEmail())) {
            usersRepository.save(newUser);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Override
    public boolean containsUser(String email) {
        return !usersRepository.findByEmail(email).isEmpty();
    }

    @Override
    public Optional<User> findByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    @Override
    public void banById(Long id) {
        usersRepository.banById(id);
    }

    @Override
    public void unBanById(Long id) {
        usersRepository.unBanById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.getUserByEmail(email);
    }

    @Override
    public void updateUser(Long id, String firstName, String lastName, MultipartFile file) {
        User user = this.getUser(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if(!file.isEmpty()){
            File avatarFile = new File("D:\\Java\\SemesterWork\\src\\main\\resources\\static\\styles\\img\\avatars\\"  + id + "." + file.getContentType().substring(6));
            try {
                file.transferTo(avatarFile);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            user.setAvatar("/styles/img/avatars/" + id + "." + file.getContentType().substring(6));
        }
        usersRepository.save(user);
    }

    @Override
    public void addedToMemorie(String owner, List<String> strings) {
        User owner1 = this.getUserByEmail(owner);
        owner1.setMemoriesCount(owner1.getMemoriesCount() + 1);
        usersRepository.save(owner1);
        for(String s: strings) {
            User user = this.getUserByEmail(s);
            user.setTogetherMemoriesCount(user.getTogetherMemoriesCount() + 1);
            usersRepository.save(user);
        }
    }


}
