package com.kofianan.lms.dashboard.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kofianan.lms.dashboard.domain.Course;
import com.kofianan.lms.dashboard.domain.dto.CourseDTO;
import com.kofianan.lms.dashboard.repository.CourseRepository;
import com.kofianan.lms.dashboard.security.AuthoritiesConstants;
import com.kofianan.lms.dashboard.service.CourseService;
import com.kofianan.lms.dashboard.web.rest.util.HeaderUtil;
import com.kofianan.lms.dashboard.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class CourseRessource {
	private final Logger log = LoggerFactory.getLogger(UserResource.class);	
	 private static final String ENTITY_NAME = "courseManagement";
	private final CourseRepository courseRepository;
	private final  CourseService courseService;

	public CourseRessource(CourseRepository courseRepository, CourseService courseService) {
		this.courseRepository = courseRepository;
		this.courseService = courseService;
	}
	
	 @PostMapping("/course")
	    @Secured(AuthoritiesConstants.ADMIN)
	    public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
	        log.debug("REST request to save Course : {}", courseDTO);
	        
	        Course addcourse = new Course();
			addcourse= courseService.getCourse(courseDTO.getId());
			
	        if (addcourse != null) {
	        	  log.debug("existing Course ");
	            return ResponseEntity.badRequest()
	                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Course cannot already have an ID"))
	                .body(null);
	            }else {
	            	  log.debug("New Course ");
	            	Course newCourse = courseService.createCourse(courseDTO);
	            	  return ResponseEntity.created(new URI("/api/course/"+ newCourse.getCourse_name()))
	                          .headers(HeaderUtil.createAlert( "A course is created with identifier " + newCourse.getId(),newCourse.getCourse_name()))
	                          .body(newCourse);	                  
	            }			
	 }
	 @GetMapping("/course")
	    public ResponseEntity<List<Course>> getAllUsers(Pageable pageable) {
	        final Page<Course> page = courseService.getAllcourse(pageable);
	        log.debug(" course are ",page.getContent());
	        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses");
	        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	    }
	


}
