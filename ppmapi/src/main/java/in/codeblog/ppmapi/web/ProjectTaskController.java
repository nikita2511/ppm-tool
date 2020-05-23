package in.codeblog.ppmapi.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.service.MapValidationErrorService;
import in.codeblog.ppmapi.service.TaskService;
@CrossOrigin
@RestController
@RequestMapping("api/project/task")
public class ProjectTaskController {
    @Autowired   
	private TaskService taskService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
     @PostMapping("")
	 public ResponseEntity<?> createTask(@Valid @RequestBody ProjectTask projectTask,BindingResult result){
    	 ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
 		if (errorMap != null)
 			return errorMap;

 		ProjectTask proj = taskService.saveOrUpdateTask(projectTask);
 		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);

	 }  

 	@GetMapping("/all")
 	public Iterable<ProjectTask> getAllTask() {
 		return taskService.findAllTask();
 	}
   @GetMapping("{Id}")
	public ResponseEntity<?> getProjectTaskByIdentifier(@PathVariable String Id){
		List<ProjectTask> projectTask=taskService.findByTaskIdentifier(Id);
		return new ResponseEntity<ProjectTask>((ProjectTask) projectTask,HttpStatus.OK);
	}
}
