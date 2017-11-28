package com.powerbridge.manifest.manifestFrame.database.mapper.primarySource;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TestMapper {

	@Select("select * from meta_tables_info order by tableDbSourceName,tableName asc")
	@Results({
	})
	List getAll();
}