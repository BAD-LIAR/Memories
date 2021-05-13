package ru.itis.memories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.memories.models.MemorieAccess;

import java.util.List;

public interface MemorieAccessRepository extends JpaRepository<MemorieAccess, Long> {


}
