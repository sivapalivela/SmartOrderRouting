package com.mthree.repositories;

import com.mthree.models.DarkPoolTransactionBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DarkPoolTransRepository extends JpaRepository<DarkPoolTransactionBook, String> {

    @Query(value = "select sum(transaction_amount) from dark_pool_transaction_book where time_stamp = ?1", nativeQuery = true)
    public Double getTransactionAmount(String d);
}
