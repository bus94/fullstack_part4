package com.ss.rest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ss.rest.dto.Cat;

@Mapper
public interface CatMapper {
	List<Cat> selectAll();

	List<Cat> selectByName(String name);

	Cat selectById(int id);
}
