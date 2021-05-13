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

    @Query(nativeQuery = true, value = "select m2.id, m2.owner_id, m2.text " +
            "from account a join memorieaccess m " +
            "on a.id = m.participant_id " +
            "join memorie m2 " +
            "on m2.id = m.memorie_id " +
            "where a.id = :id")
    List<Memorie> getAllTogether(@Param("id") Long id);



}
