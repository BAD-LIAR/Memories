package ru.itis.memories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.memories.models.Memorie;
import ru.itis.memories.models.MemorieAccess;

import java.util.List;

public interface MemorieAccessRepository extends JpaRepository<MemorieAccess, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "delete from memorieaccess where memorie_id = :id")
    void deleteAllByMemorie(@Param("id") Long id);


}
