package com.embarkx.firstjobapp.job.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {

    // private final List<Job> jobs = new ArrayList<>();
    private final JobRepository jobRepository;
    // private Long NextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        // check unique id before adding data
        // job.setId(NextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        return jobRepository.findById(id).map(job -> {
            jobRepository.delete(job);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        if (jobRepository.existsById(id)) {
            job.setId(id);
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
