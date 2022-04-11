package com.devsuperior.hrworker.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrworker.exception.WorkerException;
import com.devsuperior.hrworker.model.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import com.devsuperior.hrworker.service.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerRepository repository;
	
	@Override
	public List<Worker> findAll() {
		return repository.findAll() ;
	}

	@Override
	public Worker findById(Long id) {
		Optional<Worker> objFound = repository.findById(id);
		return objFound.orElseThrow(()-> new WorkerException(id));
	}

}
