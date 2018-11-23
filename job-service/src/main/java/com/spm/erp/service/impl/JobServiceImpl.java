package com.spm.erp.service.impl;

import com.spm.erp.model.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spm.erp.exception.AppException;
import com.spm.erp.model.Job;
import com.spm.erp.repository.JobRepository;
import com.spm.erp.service.JobService;
import com.spm.erp.util.BeanUtil;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}
	
	@Override
	public Optional<Job> findJobById(Integer id) {
		return jobRepository.findById(id);
	}

	@Override
	public CustomResponse insertJob(Job	job) {
		if (jobRepository.existsByTitle(job.getTitle())) {
			return new CustomResponse(false, "Job with title " + job.getTitle() + " is already taken!");
		}

		try {
			jobRepository.save(job);
			return new CustomResponse(true, "Job saved successfully");
		} catch (Exception ex) {
			throw new AppException("Job did not get saved.");
		}
	}

	@Override
	public void deleteJob(Integer id) {
		try {
			jobRepository.deleteById(id);
		} catch (Exception e) {
			throw new AppException("Job did not get deleted.");
		}
	}

	@Override
	public CustomResponse updateJob(Integer id, Job job) {
		Optional<Job> jobOptional = jobRepository.findById(id);
		if (!jobOptional.isPresent())
			return new CustomResponse(false, "No job found by ID: " + id);
		if (jobRepository.existsByTitle(job.getTitle())) {
			return new CustomResponse(false, "Job with title " + job.getTitle() + " is already taken!");
		}
		Job jobActual = jobOptional.get();
		jobActual = BeanUtil.<Job>copyNonNullProperties(jobActual, job);
		try {
			jobRepository.save(jobActual);
			return new CustomResponse(true, "Job updated successfully");
		} catch (Exception ex) {
			throw new AppException("Something went wrong. Job details not updated.");
		}
	}
}
