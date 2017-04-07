package com.kofianan.lms.dashboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kofianan.lms.dashboard.domain.Course;

/**
 * Spring Data MongoDB repository for the Course entity.
 */
@Repository
public interface CourseRepository extends MongoRepository<Course, String>{	
	Course findById(String courseID);
	Page<Course> findAll(Pageable pageable);	
	

}
