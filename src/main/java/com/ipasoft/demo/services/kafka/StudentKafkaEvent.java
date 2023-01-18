package com.ipasoft.demo.services.kafka;

import com.ipasoft.demo.domain.entity.StudentRedis;

public class StudentKafkaEvent extends KafkaEvent<StudentRedis> {

	public StudentKafkaEvent(StudentRedis student) {
		super(student);
	}
}