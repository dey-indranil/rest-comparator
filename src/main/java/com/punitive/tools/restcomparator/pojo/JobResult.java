package com.punitive.tools.restcomparator.pojo;

import java.util.Date;

public class JobResult {
	
	private String uniqId;
	private String result1;
	private String result2;
	private String cause1;
	private String cause2;
	private Status status;
	private Date modifiedDate;
	private Date runDate;
	public String getUniqId() {
		return uniqId;
	}
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}
	public String getResult1() {
		return result1;
	}
	public void setResult1(String result1) {
		this.result1 = result1;
	}
	public String getResult2() {
		return result2;
	}
	public void setResult2(String result2) {
		this.result2 = result2;
	}
	public String getCause1() {
		return cause1;
	}
	public void setCause1(String cause1) {
		this.cause1 = cause1;
	}
	public String getCause2() {
		return cause2;
	}
	public void setCause2(String cause2) {
		this.cause2 = cause2;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}
	@Override
	public String toString() {
		return "JobResult [uniqId=" + uniqId + ", result1=" + result1 + ", result2=" + result2 + ", cause1=" + cause1
				+ ", cause2=" + cause2 + ", status=" + status + ", modifiedDate=" + modifiedDate + ", runDate="
				+ runDate + "]";
	}
	
}
