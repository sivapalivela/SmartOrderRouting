package com.mthree.repositories;

import com.mthree.models.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface TraderRepository extends JpaRepository<Trader, String> {
}
