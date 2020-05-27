package com.yash.ppmtoolapi1.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.ppmtoolapi1.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

	ProjectTask findByProjectSequence(String sequence);
}
