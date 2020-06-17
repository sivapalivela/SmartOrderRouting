package com.mthree.repositories;

import com.mthree.models.DarkPoolTransactionBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DarkPoolTransRepository extends JpaRepository<DarkPoolTransactionBook, String> {
}
