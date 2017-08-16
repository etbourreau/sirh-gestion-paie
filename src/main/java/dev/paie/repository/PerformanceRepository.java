package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer> {
	
}
