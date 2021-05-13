package ru.itis.memories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.User;

import java.util.List;

public interface MemoriesRepository extends JpaRepository<Memorie, Long> {

//    @Transactional
//    @Modifying
//    @Query( value = "select * from Memorie", nativeQuery = true)
//    List<Memorie> getAll();

    Memorie getById(@Param("id") Long id);

    List<Memorie> getAllByOwner(User user);

}
