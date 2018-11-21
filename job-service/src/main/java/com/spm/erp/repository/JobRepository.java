package com.spm.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spm.erp.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
	Boolean existsByTitle(String title);
}
