package com.diveintodev.cabservice.repository;

import com.diveintodev.cabservice.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabRepository extends JpaRepository<Cab,Integer> {
}
