package in.codeblog.ppmapi.web;

@RequestController
@RequestMapping("api/backlog")
public class BacklogController {
	private ProjectTaskService projectService;
	public ResponseEntity<?> addPTTOBacklog(@Valid 
			@RequestBody ProjectTask projectTask,
			BindingResult result , @PathVariable String backlog_id)
	{
		responseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		
		ProjectTask newProjectTask = projectTaskService.addProject
	}
	
}
