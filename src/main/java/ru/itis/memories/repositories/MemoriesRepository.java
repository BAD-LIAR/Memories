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

//    @Query("select Memorie from Memorie where isDeleted = false ")
    List<Memorie> getAllByOwnerAndIsDeleted(User user, Boolean isDeleted);

    @Query(nativeQuery = true, value = "select m2.id, m2.owner_id, m2.text, m2.isdeleted " +
            "from account a join memorieaccess m " +
            "on a.id = m.participant_id " +
            "join memorie m2 " +
            "on m2.id = m.memorie_id " +
            "where a.id = :id and isdeleted = false")
    List<Memorie> getAllTogether(@Param("id") Long id);


//    @Query(value = "select Memorie " +
//            "from User join MemorieAccess " +
//            "on User.id = MemorieAccess.participant.id " +
//            "join Memorie " +
//            "on Memorie.id = MemorieAccess.participant.id " +
//            "where User.id = :id and Memorie.isDeleted = false ")
//    List<Memorie> getAllTogether(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "update Memorie set isDeleted = true where id = :id")
    void setIsDeletedTrue(@Param("id") Long id);


//    @Query(nativeQuery = true, value ="select  a.id, a.email, a.firstname, a.lastname " +
//            "from account a join memorieaccess m " +
//            "on a.id = m.participant_id " +
//            "where m.memorie_id = :id")
//    List<User> getPartipients(@Param("id") Long id);


    @Query(value ="select new User(a.id, a.firstName, a.lastName, a.email) " +
            "from User a join MemorieAccess m " +
            "on a.id = m.participant.id " +
            "where m.memorie.id = :id")
    List<User> getPartipients(@Param("id") Long id);


}
