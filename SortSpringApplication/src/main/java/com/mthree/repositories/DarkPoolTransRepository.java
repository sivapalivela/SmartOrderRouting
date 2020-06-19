package com.mthree.repositories;

import com.mthree.models.DarkPoolTransactionBook;
import com.mthree.models.TransactionBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DarkPoolTransRepository extends JpaRepository<DarkPoolTransactionBook, String> {

    @Query(value = "select sum(transaction_amount) from dark_pool_transaction_book where time_stamp = ?1", nativeQuery = true)
    public Double getTransactionAmount(String d);

    @Query(value = "select sum(number_of_shares) from dark_pool_transaction_book where time_stamp = ?1", nativeQuery = true)
    public Double getTransactionShares(String d);

    @Query(value = "select * from dark_pool_transaction_book where consumer = ?1", nativeQuery = true)
    public List<DarkPoolTransactionBook> findConsumerById(String userId);
}
