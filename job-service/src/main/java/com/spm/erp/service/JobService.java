package com.spm.erp.service;

import java.util.List;
import java.util.Optional;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Job;

public interface JobService {
	
	Optional<Job> findJobById(Integer id);

	List<Job> getAllJobs();
	
	CustomResponse insertJob(Job job);

	void deleteJob(Integer id);

	CustomResponse updateJob(Integer id, Job job);

}