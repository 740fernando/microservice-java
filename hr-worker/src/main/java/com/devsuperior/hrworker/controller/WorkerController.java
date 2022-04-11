package com.devsuperior.hrworker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	private static final String PORT = "PORT = ";

	private static Logger logger = LoggerFactory.getLogger(WorkerController.class);
	
	/**
	 * Anotation utilizada para acessar as informacoes da configuracao
	 */
	@Value("${test.config}")
	private String testConfig;
	/**
	 * Environment is an interface representing the environment in which the current application is running. Acessa as configuracoes
	 */
	@Autowired
	private Environment env;
	
	@Autowired
	private WorkerService service;
	
	@Autowired
	private WorkerMapper mapper;
	
	@GetMapping(value="/configs")
	public ResponseEntity<Void>getConfigs(){
		logger.info("CONFIG = "+testConfig);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>>searchAll(){
		return ResponseEntity.ok(service.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<WorkerResponseDTO> findById(@PathVariable Long id){
		/* Teste de TIMOUT*/
		try {
			Thread.sleep(3000L);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		logger.info(PORT.concat(env.getProperty("local.server.port")));
		return ResponseEntity.ok(mapper.toWorkerResponseDTO(service.findById(id)));
	}
}
