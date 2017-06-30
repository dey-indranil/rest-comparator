package com.punitive.tools.restcomparator;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.punitive.tools.restcomparator.dao.JobRunSettingDao;
import com.punitive.tools.restcomparator.pojo.Job;
import com.punitive.tools.restcomparator.pojo.JobRunSetting;
import com.punitive.tools.restcomparator.pojo.JobSetting;

@Component
public class JobFactory {
	
	@Autowired
	private JobRunSettingDao jobRunSettingDao;
	
	public List<Job> build(JobSetting jobSetting){
		List<JobRunSetting> jobRunSettingList = jobRunSettingDao.getJobRunSetting(jobSetting.getJobName());
		List<Job> jobList = jobRunSettingList.parallelStream()
				.map(x->buildJob(x, jobSetting))
				.collect(Collectors.toList());	
		return jobList;
	}

	private Job buildJob(JobRunSetting x, JobSetting jobSetting) {
		Job job = new Job();
		job.setJobRunSetting(x);
		job.setJobSetting(jobSetting);
		job.setRunDate(new Date());
		return job;
	}
}
