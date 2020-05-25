package com.yash.ppmtoolapi1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yash.ppmtoolapi1.domain.Backlog;
import com.yash.ppmtoolapi1.domain.Project;
import com.yash.ppmtoolapi1.exception.PojectIdException;
import com.yash.ppmtoolapi1.exception.ProjectIdExceptionResponse;
import com.yash.ppmtoolapi1.repository.BacklogRepository;
import com.yash.ppmtoolapi1.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId()==null) {
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			return projectRepository.save(project);
		}catch(Exception ex) {
			throw new PojectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new PojectIdException("Project Id '"+projectId+"' does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new PojectIdException("Cannot delete project wid id '"+projectId+"' this project not exist");
		}
		projectRepository.delete(project);
		
	}
}
