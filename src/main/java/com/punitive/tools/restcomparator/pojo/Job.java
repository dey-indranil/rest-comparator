package com.punitive.tools.restcomparator.pojo;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Job implements Runnable{
	private JobSetting jobSetting;
	private JobRunSetting jobRunSetting;
	
	private Date runDate;
	private Status status;
	private String reason;
	private Date modifiedDate;
	
	public JobSetting getJobSetting() {
		return jobSetting;
	}
	public void setJobSetting(JobSetting jobSetting) {
		this.jobSetting = jobSetting;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.runDate = runDate;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public JobRunSetting getJobRunSetting() {
		return jobRunSetting;
	}
	public void setJobRunSetting(JobRunSetting jobRunSetting) {
		this.jobRunSetting = jobRunSetting;
	}
	@Override
	public String toString() {
		return "JobResult [jobSetting=" + jobSetting + ", runDate=" + runDate + ", status=" + status + ", modifiedDate="
				+ modifiedDate + ", reason=" + reason + ", jobRunSetting=" + jobRunSetting + "]";
	}
	
	public String toJson() throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(this);
	}
	
	@Override
	public void run() {
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					callEndpoint(jobSetting.getUrl1());
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					callEndpoint(jobSetting.getUrl2());
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}).start();
	}
	private HttpEntity<String> callEndpoint(String url) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		MultiValueMap<String, String> params = extractParams();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParams(params);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		
		return response;
	}
	private MultiValueMap<String, String> extractParams() throws JsonParseException, JsonMappingException, IOException {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		params = mapper.readValue(jobRunSetting.getParamsJson(), new TypeReference<MultiValueMap<String, String>>(){});
		return params;
	}
	
}

enum Status{
	SUCCESS,FAILED;
}
