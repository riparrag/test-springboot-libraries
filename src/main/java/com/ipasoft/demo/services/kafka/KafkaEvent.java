package com.ipasoft.demo.services.kafka;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class KafkaEvent<T> {
	private UUID id;
	private Date date;
	private T entity;
	
	public KafkaEvent() {}
	
	public KafkaEvent(T entity) {
		this.entity = entity;
		this.setId( UUID.randomUUID() );
		this.setDate( new Date() );
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		}
		catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "error in toString";
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
}