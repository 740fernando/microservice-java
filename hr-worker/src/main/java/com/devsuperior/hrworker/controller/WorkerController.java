package com.devsuperior.hrworker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.mapper.WorkerMapper;
import com.devsuperior.hrworker.model.Worker;
import com.devsuperior.hrworker.model.dto.WorkerResponseDTO;
import com.devsuperior.hrworker.service.WorkerService;

@RestController
@RequestMapping(value="/workers")
public class WorkerController {
	
	@Autowired
	private WorkerService service;
	
	@Autowired
	private WorkerMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<Worker>>searchAll(){
		return ResponseEntity.ok(service.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(mapper.toWorkerResponseDTO(service.findById(id)));
	}

}
