package in.codeblog.ppmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codeblog.ppmapi.domain.ProjectTask;
import in.codeblog.ppmapi.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public ProjectTask saveOrUpdateTask(ProjectTask projectTask) {
		return taskRepository.save(projectTask);
	}

	public Iterable<ProjectTask> findAllTask() {
		return taskRepository.findAll();
	}

	public List<ProjectTask> findByTaskIdentifier(String id) {
		
		return  taskRepository.findByTaskIdentifier(id);
	}
}
