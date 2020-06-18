package com.mthree.repositories;

import com.mthree.models.TransactionBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionBook,String> {

    @Query(value = "select * from transaction_book where transactions_of_user = ?1", nativeQuery = true)
    public List<TransactionBook> findConsumerById(String userId);

    @Query(value = "select sum(transaction_amount) from transaction_book where time_stamp = ?1", nativeQuery = true)
    public Double getTransactionAmount(String d);
}
