package com.punitive.tools.restcomparator.pojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
import com.punitive.tools.restcomparator.dao.JobResultDao;

public class Job implements Runnable{
	private JobSetting jobSetting;
	private JobRunSetting jobRunSetting;
	private JobResultDao jobResultDao;
	
	private Date modifiedDate;
	
	public JobSetting getJobSetting() {
		return jobSetting;
	}
	public void setJobSetting(JobSetting jobSetting) {
		this.jobSetting = jobSetting;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public JobRunSetting getJobRunSetting() {
		return jobRunSetting;
	}
	public void setJobRunSetting(JobRunSetting jobRunSetting) {
		this.jobRunSetting = jobRunSetting;
	}
	
	public JobResultDao getJobResultDao() {
		return jobResultDao;
	}
	public void setJobResultDao(JobResultDao jobResultDao) {
		this.jobResultDao = jobResultDao;
	}
	@Override
	public String toString() {
		return "Job [jobSetting=" + jobSetting + ", jobRunSetting=" + jobRunSetting + ", modifiedDate=" + modifiedDate
				+ "]";
	}
	public String toJson() throws JsonProcessingException{
		return new ObjectMapper().writeValueAsString(this);
	}
	
	@Override
	public void run() {
		try {
			callEndpoint(jobSetting.getUrl1());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			callEndpoint(jobSetting.getUrl2());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
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
	*/
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
		Map<String,String> intermediateMap = null;
		ObjectMapper mapper = new ObjectMapper();
		intermediateMap = mapper.readValue(jobRunSetting.getParamsJson(), new TypeReference<Map<String, String>>(){});
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		for(String key:intermediateMap.keySet()){
			if(!params.containsKey(key))
				params.put(key, new ArrayList<>());
			params.get(key).add(intermediateMap.get(key));
		}
		return params;
	}
	
}

enum Status{
	SUCCESS,FAILED;
}
