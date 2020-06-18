package com.mthree.repositories;

import com.mthree.models.Consumers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumersRepository extends JpaRepository<Consumers,String> {

    @Query(value = "select * from consumers where consumers_id = ?1 and password = ?2", nativeQuery = true)
    public Consumers findUserById(String username, String password);
}
