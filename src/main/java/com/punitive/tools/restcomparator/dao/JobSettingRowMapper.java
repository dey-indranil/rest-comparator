package com.punitive.tools.restcomparator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.punitive.tools.restcomparator.pojo.JobSetting;

public class JobSettingRowMapper implements RowMapper<JobSetting> {

	@Override
	public JobSetting mapRow(ResultSet rs, int line) throws SQLException {
		JobSetting jobSetting = new JobSetting();
		jobSetting.setJobName(rs.getString("job_name"));
		jobSetting.setUrl1(rs.getString("url1"));
		jobSetting.setUrl2(rs.getString("url2"));
		jobSetting.setIsActive(rs.getString("is_active"));
		jobSetting.setModifiedDate(rs.getDate("modified_dt"));
		
		return jobSetting;
	}

}
