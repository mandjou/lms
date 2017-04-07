package com.kofianan.lms.dashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kofianan.lms.dashboard.domain.Course;
import com.kofianan.lms.dashboard.domain.dto.CourseDTO;
import com.kofianan.lms.dashboard.repository.CourseRepository;
import com.kofianan.lms.dashboard.service.dto.UserDTO;

/**
 * Service class for managing course.
 */
@Service
public class CourseService {
	private final Logger log = LoggerFactory.getLogger(CourseService.class);

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;		
	}

	public Course getCourse(String courseID) {
		return courseRepository.findById(courseID);
	}
	public Page<Course> getAllcourse(Pageable pageable) {
		log.info("courses is",courseRepository.findAll(pageable).getContent());
		return courseRepository.findAll(pageable);
	}

	public Course createCourse(CourseDTO coursedto) {		 
		Course course = new Course();
		course.setId(coursedto.getId());
		course.setCourse_name(coursedto.getCourse_name());
		course.setCourse_type(coursedto.getCourse_type());
		course.setActivated(false);
		course.setCourse_faculty(coursedto.getCourse_faculty());
		// course.setCourse_Dept(Course_Dept);
		courseRepository.save(course);		 
		return course;

	}

	



}
