package com.group.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group.sample.entity.Test;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@Repository
@RepositoryRestResource
public interface TestRepository extends JpaRepository<Test, String>{

	@RestResource(path="namesList")
	@Query("select test.name from Test test")
	public List<String> getNames();
	
}
