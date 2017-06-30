package com.punitive.tools.restcomparator.pojo;

import java.sql.Date;

public class JobSetting {
	private String jobName;
	private String url1;
	private String url2;
	private Date modifiedDate;
	private String isActive;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "JobSetting [jobName=" + jobName + ", url1=" + url1 + ", url2=" + url2 + ", modifiedDate=" + modifiedDate
				+ ", isActive=" + isActive + "]";
	}
	
}
