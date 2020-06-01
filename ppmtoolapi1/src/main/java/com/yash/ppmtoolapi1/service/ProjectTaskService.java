package com.yash.ppmtoolapi1.service;

import java.util.List;

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
		// Make sure that backlog id exists
		Backlog backlog=backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog==null) {
			throw new ProjectNotFoundException("Project with id: '"+backlog_id+"' does not exist");
		}
		
		//make sure that task exists
		ProjectTask projectTask=projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask==null) {
			throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist");
		}
		
		//make sure that backlog/project_id in path corresponds to right project
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project Task '"+pt_id+"' does not exist in project: '"+backlog_id+"'");
		}
		return projectTask;
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_id,String pt_id) {
		ProjectTask projectTask=findPTByProjectSequence(backlog_id,pt_id);
		projectTask=updatedTask;
		return projectTaskRepository.save(projectTask);
	}
	
	public void deletePTByProjectSequence(String backlog_id,String pt_id) {
		ProjectTask projectTask=findPTByProjectSequence(backlog_id, pt_id);
		Backlog backlog=projectTask.getBacklog();
		List<ProjectTask> pts=backlog.getProjectTasks();
		pts.remove(projectTask);
		backlogRepository.save(backlog);
		projectTaskRepository.delete(projectTask);
	}
}
