package in.codeblog.ppmapi.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codeblog.ppmapi.domain.Project;
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
          
	          @Override
	              Iterable<Project> findAll();
	          
	          
	          @Override
	        Iterable<Project> findAllById(Iterable<Long> ids);
	        
         

	      	Project findByProjectIdentifier(String projectId);
} 
