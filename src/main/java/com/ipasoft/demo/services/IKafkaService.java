package com.ipasoft.demo.services;

import com.ipasoft.demo.domain.entity.StudentRedis;

public interface IKafkaService {
	public String produceMessageInkafka(String mensaje);
	public String produceStudentKafkaEvent(StudentRedis student);
}