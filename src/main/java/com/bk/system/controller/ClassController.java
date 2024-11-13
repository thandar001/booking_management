package com.bk.system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bk.system.controller.api.exception.BeanValidationException;
import com.bk.system.controller.api.exception.DataNotFoundException;
import com.bk.system.model.dto.ClassDto;
import com.bk.system.service.ClassService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/classes")
public class ClassController {
	
	@Autowired
	ClassService classService;
	

	
	@GetMapping
	List<ClassDto> getAllClass()  {
		return this.classService.getAllClass();
	}
	
	@GetMapping("{classId}")
	ResponseEntity<ClassDto> getClassById(@PathVariable Long classId) throws DataNotFoundException {
		Optional<ClassDto> classDto = this.classService.getClassByID(classId);
		if (classDto.isPresent()) {
			return ResponseEntity.of(classDto);
		} else {
			throw new DataNotFoundException("Class " + classId + " Not Found");
		}
	}
	
	@PostMapping()
	public ResponseEntity<ClassDto> createClass(@Valid @RequestBody ClassDto classDto, BindingResult bindingResult)
			throws BeanValidationException {
		log.info("binding Result" + bindingResult.hasErrors());
		if (!bindingResult.hasErrors()) {
			ClassDto insertedData = this.classService.createClass(classDto);
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(insertedData);

		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}
	}
	
	@PutMapping("{classId}")
	public ResponseEntity<ClassDto> updateClass(@PathVariable Long classId, @Valid @RequestBody ClassDto classDto,
			BindingResult bindingResult) throws BeanValidationException, DataNotFoundException {
		if (!bindingResult.hasErrors()) {
			Optional<ClassDto> cDto = this.classService.getClassByID(classId);
			if (cDto.isPresent()) {
				classDto.setId(classId);
				ClassDto insertedData = this.classService.updateClass(classDto);
				return ResponseEntity.ok(insertedData);

			} else {
				throw new DataNotFoundException("Class Id " + classId + " Not Found");
			}
		} else {
			throw new BeanValidationException(bindingResult.getAllErrors());
		}

	}
	
	@DeleteMapping("{classId}")
	public ResponseEntity<ClassDto> deleteClass(@PathVariable Long classId) throws DataNotFoundException {
		Optional<ClassDto> cDto = this.classService.getClassByID(classId);
		if (cDto.isPresent()) {
			Optional<ClassDto> result = this.classService.deleteClass(classId);
			return ResponseEntity.ok(result.get());
		} else {
			throw new DataNotFoundException("Class " + classId + " Not Found");
		}

	}
	
	
	

}
