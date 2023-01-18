package com.ipasoft.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ipasoft.demo.domain.entity.StudentRedis;
import com.ipasoft.demo.domain.repository.IStudentRedisRepository;
import com.ipasoft.demo.services.IKafkaService;

@RestController
@RequestMapping("/students")
public class StudentRedisController {

	@Autowired
	private IStudentRedisRepository studentRedisRepository;
	
	@Autowired
	private IKafkaService kafkaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentRedis> findById(@PathVariable String id) {
		return new ResponseEntity<StudentRedis>( studentRedisRepository.findById(id).orElse(null), HttpStatus.OK );
	}
	
	
	@GetMapping("/get-all")
	public Iterable<StudentRedis> getAll() {
		return studentRedisRepository.findAll();
	}
	
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createStudent(@RequestBody StudentRedis student) {
		studentRedisRepository.save( student );
		
		kafkaService.produceMessageInkafka("student magico "+student.getName()+" creado no te la puedo creer!"); 
		kafkaService.produceStudentKafkaEvent( student );
	}
	
	@PutMapping("/{id}")
	public void updateStudent(@RequestBody StudentRedis student, @PathVariable() String id) {
		//StudentRedis actualStudent = studentRedisRepository.findById(id).orElseThrow();
		
		//actualStudent.setName( student.getName() ); 
		
		studentRedisRepository.save( student );
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable() String id) {
		studentRedisRepository.deleteById(id);
	}
}