package com.mthree.repositories;

import com.mthree.models.Consumers;
import com.mthree.models.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface TraderRepository extends JpaRepository<Trader, String> {

    @Query(value = "select * from trader where trader_id = ?1 and password = ?2", nativeQuery = true)
    public Trader findTraderById(String username, String password);
}
