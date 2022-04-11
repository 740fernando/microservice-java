package com.devsuperior.hrouath.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude ={"id"})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String roleName;
	
}
