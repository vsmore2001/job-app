package com.embarkx.firstjobapp.job;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    
    /**
     * Fetch all jobs.
     * @return List of all jobs.
     */
    @GetMapping("/alljobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.findAll();
        if (jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(jobs);
    }

    /**
     * Add a new job.
     * @param job Job object from request body.
     * @return Success message.
     */

    @PostMapping("/addjobs")
    public ResponseEntity<String> addJob(@RequestBody Job job) {
        jobService.createJob(job);
        return ResponseEntity.status(201).body("Job added successfully");
    }

    /**
     * Fetch job by ID.
     * @param id Job ID.
     * @return Job object if found, otherwise 404 status.
     */
    @GetMapping("getjobbyid/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return (job != null) ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("deletejobbyid/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean deleted = jobService.deleteJobById(id);
        if (deleted) {
            return ResponseEntity.ok("Job deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
    }

    @PutMapping("/updatejobbyid/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job) {
        boolean updated = jobService.updateJobById(id, job);
        if (updated) {
            return ResponseEntity.ok("Job updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
    }
}
