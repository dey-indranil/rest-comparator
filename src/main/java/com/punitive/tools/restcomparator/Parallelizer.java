package com.punitive.tools.restcomparator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

@Component
public class Parallelizer{
	private ExecutorService executorService = Executors.newCachedThreadPool();

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}
	
}
