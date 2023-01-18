package com.ipasoft.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ipasoft.demo.domain.entity.StudentRedis;
import com.ipasoft.demo.services.kafka.KafkaEvent;
import com.ipasoft.demo.services.kafka.StudentKafkaEvent;

@Service
public class KafkaService implements IKafkaService {
	
	private final String TOPIC = "ipa_kafka_topic";
	private final String TOPIC_STUDENT = "student_kafka_topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTemplate<String, KafkaEvent<?>> kafkaStudentTemplate;

	@Override
	public String produceMessageInkafka(String mensaje) {
		
		kafkaTemplate.send(TOPIC, mensaje);
		
		return "produced";
	}
	
	@Override
	public String produceStudentKafkaEvent(StudentRedis student) {
		StudentKafkaEvent event = new StudentKafkaEvent( student );
		
		kafkaStudentTemplate.send(TOPIC_STUDENT, event);
		
		return "student kafka event produced";
	}
	
	@KafkaListener(topics = TOPIC, groupId = "group-id")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }
	
	@KafkaListener(topics = TOPIC_STUDENT, groupId = "group-id")
    public void listen(KafkaEvent<?> event) {
        System.out.println("student kafka event: " + event.toString());
    }
}
