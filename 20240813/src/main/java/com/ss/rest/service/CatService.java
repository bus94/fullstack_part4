package com.ss.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.rest.dto.Cat;
import com.ss.rest.mapper.CatMapper;

@Service
public class CatService {
	
	@Autowired
	private CatMapper mapper;

	public List<Cat> selectAll() {
		return mapper.selectAll();
	}

	public List<Cat> selectByName(String name) {
		return mapper.selectByName(name);
	}

	public Cat selectById(int id) {
		return mapper.selectById(id);
	}

	
}
