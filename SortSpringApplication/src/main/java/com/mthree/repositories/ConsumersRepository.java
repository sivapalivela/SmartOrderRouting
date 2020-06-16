package com.mthree.repositories;

import com.mthree.models.Consumers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumersRepository extends JpaRepository<Consumers,String> {
}
