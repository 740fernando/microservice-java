package com.devsuperior.hrworker.model.dto;

import lombok.Data;

@Data
public class WorkerResponseDTO {

	private Long id;
	private String name;
	private Double dailyIncome;
}
