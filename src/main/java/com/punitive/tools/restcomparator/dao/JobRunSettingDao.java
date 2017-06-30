package com.punitive.tools.restcomparator.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.punitive.tools.restcomparator.pojo.JobRunSetting;

@Repository
public class JobRunSettingDao {
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	public List<JobRunSetting> getAllJobRunSettings() {
        return jdbcTemplate.query("select job_name,"
        		+ " params_json, modified_dt"
        		+ " from job_run_setting",
                new JobRunSettingRowMapper());
    }
	
	public List<JobRunSetting> getJobRunSetting(String jobName) {
        return jdbcTemplate.query("select job_name,"
        		+ " params_json, modified_dt"
        		+ " from job_run_setting"
        		+ " where job_name = ?",
        		new Object[]{jobName},
                new JobRunSettingRowMapper());
    }
}
