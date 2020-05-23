package in.codeblog.ppmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.Project;
import in.codeblog.ppmapi.exception.ProjectIdException;
import in.codeblog.ppmapi.repository.ProjectRepository;
@Service
public class ProjectService {
    @Autowired     
	private ProjectRepository projectRepository;
       
    public Project saveOrUpdateProject(Project project) {
    	try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch(Exception ex) {
			throw new ProjectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
    }
     public Project findProjectByIdentifier(String projectId) {
		Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' does not exists");
		}
		return project;
	}
    
    
    public void deleteProjectByIdentifier(String projectId) {
    	  Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    	  if(project==null) {
  			throw new ProjectIdException("Project Id '"+projectId.toUpperCase()+"' does not exists");
  		}
  		projectRepository.delete(project);
    	  
    }
    
       public Iterable<Project> findAllProjects(){
    	   return projectRepository.findAll();
       }
}
