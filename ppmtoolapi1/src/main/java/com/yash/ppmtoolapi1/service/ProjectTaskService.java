package com.yash.ppmtoolapi1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.yash.ppmtoolapi1.domain.Backlog;
import com.yash.ppmtoolapi1.domain.Project;
import com.yash.ppmtoolapi1.domain.ProjectTask;
import com.yash.ppmtoolapi1.exception.ProjectNotFoundException;
import com.yash.ppmtoolapi1.repository.BacklogRepository;
import com.yash.ppmtoolapi1.repository.ProjectRepository;
import com.yash.ppmtoolapi1.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask) {
		
		//Exceptions: Project Not Found
		
		//ProjectTasks to be added to specific project,project!=null,Backlog exists
		
         try {
        	 Backlog backlog=backlogRepository.findByProjectIdentifier(projectIdentifier);
     		
     		//Set the backlog to project task
     		projectTask.setBacklog(backlog);
     		
     		//We want our project sequence to be like: IDPRO-1,IDPRO-2
     		
     		Integer backLogSequence=backlog.getPTSequence();
     		
     		System.out.println("Backlog Sequence = "+backLogSequence);
     		backLogSequence++;
     		
     		backlog.setPTSequence(backLogSequence);
     		
     		//Add backlogSequence to project task
     		projectTask.setProjectSequence(projectIdentifier+"-"+backLogSequence);
     		
     		projectTask.setProjectIdentifier(projectIdentifier);
     		
     		//Initial priority when priority is null
     		if(projectTask.getPriority()==null) {
     			projectTask.setPriority(3);
     		}
     		
     		//Initial status when status is null
     		
     		if(projectTask.getStatus()==""|| projectTask.getStatus()==null) {
     			projectTask.setStatus("TO_DO");
     		}
     		
     		return projectTaskRepository.save(projectTask);
     		
			
		 }catch(Exception ex) {
			 throw new ProjectNotFoundException("Project Not Found");
		 }
		
	}
	
	public Iterable<ProjectTask> findBacklogById(String id){
		
		Project project=projectRepository.findByProjectIdentifier(id);
		
		if(project==null) {
			throw new ProjectNotFoundException("Project with id: '"+id+"' does not exist");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
		
	}

	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
		// TODO Auto-generated method stub
		return projectTaskRepository.findByProjectSequence(pt_id);
	}
}
