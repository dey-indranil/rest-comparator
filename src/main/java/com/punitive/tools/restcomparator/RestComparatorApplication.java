package com.punitive.tools.restcomparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.punitive.tools.restcomparator.dao.JobSettingDao;

@SpringBootApplication
public class RestComparatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestComparatorApplication.class, args);
	}
}

@RestController
class RestComparatorController{
	@Autowired 
	private RestComparatorService restComparatorService;
	
	@RequestMapping("/invokeJob")
    public String invokeJob(@RequestParam(value = "jobName", required = false) String jobName) {
        restComparatorService.doCompare(jobName);
        return "invoke success";
    }
	
	
	@RequestMapping("/test")
    public String test() {
        return "tested:OK";
    }
}

@Service
class RestComparatorService {
	
	@Autowired 
	private JobSettingDao jobSettingDao;
	
	@Autowired
	private JobManager jobManager;
	
	public void doCompare(String jobName){
		if(StringUtils.isEmpty(jobName)){
			System.out.println(jobSettingDao.getAllJobSettings());
			jobSettingDao.getAllJobSettings().parallelStream().forEach(x->jobManager.createAndRun(x));
		}  else {
			System.out.println(jobSettingDao.getJobSetting(jobName));
			jobManager.createAndRun(jobSettingDao.getJobSetting(jobName));
		}
	}
}
