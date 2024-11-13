package com.bk.system.service;

import java.util.List;
import java.util.Optional;

import com.bk.system.model.dto.ClassDto;

public interface ClassService {
	
    List<ClassDto> getAllClass();
	 
	 Optional<ClassDto> getClassByID(Long Id);
	 
	 ClassDto createClass(ClassDto packagedDto);
	 
	 ClassDto updateClass(ClassDto packageDto);

	 Optional<ClassDto> deleteClass(Long Id);

}
