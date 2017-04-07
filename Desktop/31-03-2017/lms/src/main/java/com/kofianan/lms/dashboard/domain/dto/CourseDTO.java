package com.kofianan.lms.dashboard.domain.dto;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class CourseDTO {

	@Id
	private String id;

	@Size(max = 20)
	private String Course_name;

	@Size(max = 50)
	private String Course_type;

	@Size(max = 50)
	private String Course_faculty;

	@Size(max = 50)
	private String Course_Dept;
	
	private boolean activated = false;

}
