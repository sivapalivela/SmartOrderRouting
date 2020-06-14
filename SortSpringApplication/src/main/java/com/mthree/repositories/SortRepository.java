package com.mthree.repositories;

import com.mthree.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortRepository extends JpaRepository<Sort, String> {
}
