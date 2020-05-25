package in.codeblog.ppmapi.service;

import in.codeblog.ppmapi.domain.ProjectTask;

@Service
public class ProjectTaskService {
     @Autowired
	private BacklogRepository backlogRepository;
	
	
     @Autowired
 	private ProjectTaskRepository projectTaskRepository;
     
     public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask)
     {
    	 //Exception handeling : In case project is not available
    	 
    	 //ProjectTask should be added to a specific Project ,Project!=null,backlog exist
    	 Backlog backlog = backlogRepository.findByProjectIdentifier(findByProjectIdentifier);
    	 
    	 // set the backlog to project task
    	 ProjectTask .setBacklog(backlog);
    	 
    	 //ProjectTask request should be like :projId-1
    	 Integer backlogSequence = backlog.getPTSequence();
    	 
    	 //update projecttask sequence no.
    	 backlogSequence++;
    	 projectTask.setProjectIdentifier(projectIdentifier+"_" +backlogSequence);//FP01-1
    	 
    	projectTask.setProjectIdentifier(projectIdentifier);
    	
    	//
    	//
    	if(projectTask.getPriority()==null) {
    		projectTask.setPriority(3);
    	}
    	if(projectTask.getStatus()==""&& projectTask.getStatus()==null)
    	{
    		projectTask.setStatus("TODO");
    		return projectTaskRepository.save(projectTask);
    		
    	}
    	 
    	 
    	 
    	 
    	 
    	 
     }
}
