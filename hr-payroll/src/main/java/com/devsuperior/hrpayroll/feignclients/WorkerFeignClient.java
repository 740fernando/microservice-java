package com.devsuperior.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.devsuperior.hrpayroll.model.dto.WorkerResponseDTO;

@Component
@FeignClient(name = "hr-worker", url= "localhost:8001", path= "/workers" )
public interface WorkerFeignClient {
	
	@GetMapping(value = "/{id}")
	ResponseEntity<WorkerResponseDTO> findById(@PathVariable Long id);

}
