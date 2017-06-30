package com.punitive.tools.restcomparator.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.punitive.tools.restcomparator.pojo.JobRunSetting;

public class JobRunSettingRowMapper implements RowMapper<JobRunSetting> {

	@Override
	public JobRunSetting mapRow(ResultSet rs, int line) throws SQLException {
		JobRunSetting jobRunSetting = new JobRunSetting();
		jobRunSetting.setJobName(rs.getString("job_name"));
		jobRunSetting.setParamsJson(rs.getString("params_json"));
		jobRunSetting.setModifiedDate(rs.getDate("modified_dt"));
		
		return jobRunSetting;
	}

}
