package com.mthree.repositories;

import com.mthree.models.TradingCompanies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingCompaniesRepository extends JpaRepository<TradingCompanies, String> {
}
