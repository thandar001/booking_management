package com.bk.system.common;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class Mapper {
	
	ModelMapper modelMapper = new ModelMapper();
	
	public Mapper() {
		log.info("Mapper Initialize");
		
	}
	
	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}
	
	public ModelMapper getMapper() {
		return this.modelMapper;
	}

}
