package com.ipasoft.demo.domain.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipasoft.demo.domain.entity.StudentRedis;

public interface IStudentRedisRepository extends CrudRepository<StudentRedis, String> {
	
}