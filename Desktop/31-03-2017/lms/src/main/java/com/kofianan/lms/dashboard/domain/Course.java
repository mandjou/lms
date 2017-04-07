package com.kofianan.lms.dashboard.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Document(collection = "lms_course")
public class Course  extends AbstractAuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
	
	@Size(max = 20)
    @Field("course_name")
    private String course_name;
	
	@Size(max = 50)
    @Field("course_type")
    private String course_type;
	
	@Size(max = 50)
    @Field("course_faculty")
    private String course_faculty;
	
	@Size(max = 50)
    @Field("course_Dept")
    private String course_Dept;
	
	private boolean activated = false;

}
