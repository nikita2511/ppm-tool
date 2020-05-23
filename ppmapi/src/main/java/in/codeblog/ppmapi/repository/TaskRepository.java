package in.codeblog.ppmapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import in.codeblog.ppmapi.domain.ProjectTask;

public interface TaskRepository extends CrudRepository<ProjectTask, Long> {

	
	    
	         Iterable<ProjectTask> findAll();
	
	         List<ProjectTask> findByTaskIdentifier(String taskIdentifier);
	         
	         Optional<ProjectTask> findById(Long id);
	         
}
