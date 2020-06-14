package com.mthree.repositories;

import com.mthree.models.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<OrderStock,Integer> {

    @Modifying
    @Transactional
    @Query(value = "update order_stock set number_of_shares = ?1 where order_id = ?2",nativeQuery = true)
    public int updateOrderByNumberOfShares(int numberofshares, int id);

    @Modifying
    @Transactional
    @Query(value = "delete from order_stock where order_id = ?1", nativeQuery = true)
    public void deleteByOrderId(int id);
}
