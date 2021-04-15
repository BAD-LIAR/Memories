package ru.itis.memories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.memories.dto.UserDto;
import ru.itis.memories.dto.UserForm;
import ru.itis.memories.models.User;
import ru.itis.memories.repositories.UsersRepository;



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
                .build();
        if (!containsUser(newUser.getEmail())) {
            usersRepository.save(newUser);
            return true;
        }
        return false;
    }

    @Override
    public UserDto getUser(Long userId) {
        return UserDto.from(usersRepository.findById(userId).orElse(null));
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


}
