package com.punitive.tools.restcomparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.punitive.tools.restcomparator.pojo.JobSetting;

@Component
public class JobManager {
	
	@Autowired
	private JobFactory jobFactory;

	public void createAndRun(JobSetting jobSetting) {
		// TODO Auto-generated method stub
		
	}
	
}
