package ru.itis.memories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.memories.models.User;


import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("update User set state = 'BANNED' where id = :id")
    void banById(@Param("id") Long id);

    Optional<User> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("update User set state = 'ACTIVE' where id = :id")
    void unBanById(@Param("id") Long id);

    User getUserByEmail(String email);
}
