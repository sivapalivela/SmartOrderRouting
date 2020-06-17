package com.mthree.repositories;

import com.mthree.models.DarkPoolOrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface DarkPoolOrderRepository extends JpaRepository<DarkPoolOrderBook, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from dark_pool_order_book where order_id = ?1", nativeQuery = true)
    public void deleteByOrderId(int id);
}
