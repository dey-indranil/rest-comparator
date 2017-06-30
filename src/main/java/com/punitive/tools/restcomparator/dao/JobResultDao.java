package com.punitive.tools.restcomparator.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.punitive.tools.restcomparator.pojo.JobSetting;

@Repository
public class JobResultDao {
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	public List<JobSetting> getAllJobSettings() {
        return jdbcTemplate.query("select job_name ,"
        		+ " url1 , url2, is_active , modified_dt "
        		+ " from job_setting"
        		+ " where is_active = ?",
        		new Object[]{"Y"},
                new JobSettingRowMapper());
    }
	
	public JobSetting getJobSetting(String jobName) {
        List<JobSetting> jobSettingList =  jdbcTemplate.query("select job_name ,"
        		+ " url1 , url2, is_active , modified_dt "
        		+ " from job_setting"
        		+ " where is_active = ?"
        		+ " and job_name = ?",
        		new Object[]{"Y",jobName},
                new JobSettingRowMapper());
        if(!CollectionUtils.isEmpty(jobSettingList))
        	return jobSettingList.get(0);
        return null;
    }
}
