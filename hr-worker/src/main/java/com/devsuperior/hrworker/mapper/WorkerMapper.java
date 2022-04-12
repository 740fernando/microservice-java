package com.devsuperior.hrworker.mapper;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<WorkerResponseDTO> toWorkerListResponseDTO(List<Worker> request) {
		return request.stream().map(rqt->toWorkerResponseDTO(rqt)).collect(Collectors.toList());
	}
}
