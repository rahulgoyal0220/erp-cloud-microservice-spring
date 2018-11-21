package com.spm.erp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.erp.model.CustomResponse;
import com.spm.erp.model.Job;
import com.spm.erp.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {
	
	@Autowired
	JobService jobService;

	@GetMapping("/job")
	public ResponseEntity<?> getAllJobs() {
		return ResponseEntity.ok(jobService.getAllJobs());
	}

	@GetMapping("/job/{job_id}")
	public ResponseEntity<?> getJobById(@PathVariable("job_id") Integer id) {
		Optional<Job> job = jobService.findJobById(id);
		return ResponseEntity.ok(job.isPresent() ? job.get()
				: new CustomResponse(Boolean.FALSE, "No job found by ID: " + id));
	}

	@DeleteMapping("/job/{job_id}")
	public ResponseEntity<?> deleteJob(@PathVariable("job_id") Integer id) {
		Optional<Job> job = jobService.findJobById(id);
		if (!job.isPresent()) {
			return ResponseEntity.ok(new CustomResponse(Boolean.FALSE, "No job found with id:" + id));
		}
		jobService.deleteJob(id);
		return ResponseEntity.ok(new CustomResponse(Boolean.TRUE, "Job deleted successfully."));
	}	

	@PostMapping("/job")
	public ResponseEntity<?> insertJob(@Valid @RequestBody Job job) {
		CustomResponse response = jobService.insertJob(job);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/job/{job_id}")
	public ResponseEntity<?> updateJob(@PathVariable("job_id") Integer id,@RequestBody Job job) {
		CustomResponse response= jobService.updateJob(id, job);
		return ResponseEntity.ok(response);
	}
	
}