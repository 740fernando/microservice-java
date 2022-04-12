package com.devsuperior.hrworker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrworker.mapper.WorkerMapper;
import com.devsuperior.hrworker.model.Worker;
import com.devsuperior.hrworker.model.dto.WorkerResponseDTO;
import com.devsuperior.hrworker.service.WorkerService;

@RefreshScope
@RestController
@RequestMapping(value="/workers")
public class WorkerController {
	

	private static final String LOCAL_SERVER_PORT = "local.server.port";

	private static final String PORT = "PORT = ";

	private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerService service;
	
	@Autowired
	private WorkerMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<WorkerResponseDTO>>searchAll(){
		return ResponseEntity.ok(mapper.toWorkerListResponseDTO(service.findAll()));
	}
	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponseDTO> findById(@PathVariable Long id){
		logger.info(PORT.concat(env.getProperty(LOCAL_SERVER_PORT)));
		return ResponseEntity.ok(mapper.toWorkerResponseDTO(service.findById(id)));
	}
}
