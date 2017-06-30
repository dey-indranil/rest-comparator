package com.punitive.tools.restcomparator.pojo;

import java.sql.Date;

public class JobRunSetting {
	private String jobName;
	private String paramsJson;
	private Date modifiedDate;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getParamsJson() {
		return paramsJson;
	}
	public void setParamsJson(String paramsJson) {
		this.paramsJson = paramsJson;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	@Override
	public String toString() {
		return "JobRunSetting [jobName=" + jobName + ", paramsJson=" + paramsJson + ", modifiedDate=" + modifiedDate
				+ "]";
	}
	
	
}
