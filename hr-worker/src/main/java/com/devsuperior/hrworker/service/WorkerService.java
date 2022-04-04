package com.devsuperior.hrworker.service;

import java.util.List;

import com.devsuperior.hrworker.model.Worker;

public interface WorkerService {

	List<Worker> findAll();
	
	Worker findById(Long id);
}
