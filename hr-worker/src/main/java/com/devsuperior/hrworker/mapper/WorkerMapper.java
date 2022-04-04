package com.devsuperior.hrworker.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.devsuperior.hrworker.model.Worker;
import com.devsuperior.hrworker.model.dto.WorkerResponseDTO;

@Component
public class WorkerMapper {

	public WorkerResponseDTO toWorkerResponseDTO(Worker request) {
		var ouputDTO= new WorkerResponseDTO();
		BeanUtils.copyProperties(request, ouputDTO);
		return ouputDTO;
	}
}
