package com.ipasoft.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ipasoft.demo.services.kafka.KafkaEvent;

@Configuration
public class KafkaProducerConfig {

	private final String BOOTSTRAP_ADDRESS = "localhost:9092";
	
	@Bean()
	public ProducerFactory<String,String> producerKafkaStringFactory() {
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,String> kafkaStringTemplate() {
		return new KafkaTemplate<>( producerKafkaStringFactory() );
	}
	
	@Bean()
	public ProducerFactory<String,KafkaEvent<?>> producerKafkaJsonFactory() {
		Map<String,Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_ADDRESS);
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configs);
	}
	
	@Bean
	public KafkaTemplate<String,KafkaEvent<?>> kafkaJsonTemplate() {
		return new KafkaTemplate<String,KafkaEvent<?>>( producerKafkaJsonFactory() );
	}
}
