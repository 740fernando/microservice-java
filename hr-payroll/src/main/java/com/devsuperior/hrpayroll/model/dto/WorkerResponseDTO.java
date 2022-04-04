package com.devsuperior.hrpayroll.model.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String name;
	private Double dailyIncome;
	
}
