package com.kofianan.lms.dashboard.repository;

import com.kofianan.lms.dashboard.domain.Authority;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Authority entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String>{

}
