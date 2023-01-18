package com.ipasoft.demo.services.kafka;

import java.util.Date;
import java.util.UUID;

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