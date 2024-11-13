package com.bk.system.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bk.system.common.Mapper;
import com.bk.system.dao.ClassDao;
import com.bk.system.dao.PackageDao;
import com.bk.system.model.dto.ClassDto;
import com.bk.system.model.entity.Classes;
import com.bk.system.model.entity.Packaged;
import com.bk.system.service.ClassService;
@Service
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	ClassDao classDao;
	
	@Autowired
	PackageDao packageDao;
	
	@Autowired
	Mapper mapper;

	@Override
	public List<ClassDto> getAllClass() {
		List<ClassDto> classDtolist = new ArrayList<ClassDto>();
		List<Classes> packageEntity = this.classDao.findAll();
		classDtolist = this.mapper.mapList(packageEntity, ClassDto.class);
		return classDtolist;
	}

	@Override
	public Optional<ClassDto> getClassByID(Long Id) {
		ClassDto ClassDtoData = new ClassDto();
		Optional<Classes> result = this.classDao.findById(Id);
		if (result.isPresent()) {
			ClassDtoData = mapper.getMapper().map(result.get(), ClassDto.class);
			return Optional.of(ClassDtoData);
		} else {
			return Optional.empty();
		}
	}

	@Override
	public ClassDto createClass(ClassDto classDto) {
		Classes classEntity = this.mapper.getMapper().map(classDto, Classes.class);
		Optional<Packaged> result = this.packageDao.findById(classDto.getPackaged().getId());
		
		classEntity.setPackaged(result.get());
		
		this.classDao.save(classEntity);
		ClassDto saveclasses= mapper.getMapper().map(classEntity, ClassDto.class);
		return saveclasses;
	}

	@Override
	public ClassDto updateClass(ClassDto classdto) {
		//Classes packagedEntity = mapper.getMapper().map(classdto, Classes.class);
		Optional<Classes> classResult = this.classDao.findById(classdto.getId());
		Classes c = classResult.get();
		c.setClassName(classdto.getClassName());
		c.setRequiredCredit(classdto.getRequiredCredit());
		c.setAvailableSlots(classdto.getAvailableSlots());
		c.setStartDate(classdto.getStartDate());
		c.setEndDate(classdto.getEndDate());
		c.setMaxSlots(classdto.getMaxSlots());
		this.classDao.save(c);
		ClassDto saveClass = mapper.getMapper().map(c, ClassDto.class);
		return saveClass;
	}

	@Override
	public Optional<ClassDto> deleteClass(Long Id) {
		Optional<Classes> ClassesResult = this.classDao.findById(Id);
		ClassDto classDTO = mapper.getMapper().map(ClassesResult.get(), ClassDto.class);
		this.classDao.deleteById(Id);
		return Optional.of(classDTO);
	}

}
