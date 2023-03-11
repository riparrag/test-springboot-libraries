package com.ipasoft.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
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

import com.ipasoft.demo.controllers.dto.ResponseApiEntityBaseDTO;
import com.ipasoft.demo.domain.entity.StudentRedis;
import com.ipasoft.demo.domain.repository.IStudentRedisRepository;
import com.ipasoft.demo.services.IKafkaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/students")
public class StudentRedisController {

	@Autowired
	private IStudentRedisRepository studentRedisRepository;
	
	@Autowired
	private IKafkaService kafkaService;

	/**
	 * 
	 * @param id id of student object in redis
	 * @param httpServletRequest
	 * @return student searched by id
	 */
	@Operation(summary = "gets a student from redis", description = "Description of this method by ipasoft")
	//@PathVariable(name = "id", required = true, value = "un id existente en Redis: ejemplo 13")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "encontrado xitosamente papurri!"),
            @ApiResponse(responseCode = "404", description = "no te lo encuentra mocho!"),
            @ApiResponse(responseCode = "500", description = "pinchó atroden"),
    })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseApiEntityBaseDTO<StudentRedis>> findById(@PathVariable String id, HttpServletRequest httpServletRequest) {
		ResponseApiEntityBaseDTO<StudentRedis> response = new ResponseApiEntityBaseDTO<StudentRedis>( httpServletRequest );
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			response.setData(
				studentRedisRepository.findById(id).orElse(null) 
			);
		}
		catch (IllegalArgumentException e) {
			response.addError("NOT_FOUND","id is null", e.getMessage());
			httpStatus = HttpStatus.NOT_FOUND;
		}
		catch (RedisConnectionFailureException e) {
			response.addError("REDIS_ERROR","Error conectando a Redis: ".concat(id), e.getMostSpecificCause().getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		catch (Exception e) {
			response.addError("ERROR","Error searching student with id: ".concat(id), e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ResponseApiEntityBaseDTO<StudentRedis>>(response, httpStatus);
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