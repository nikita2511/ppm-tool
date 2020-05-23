package in.codeblog.ppmapi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ProjectTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    @NotBlank(message = "task name is required")
	private String taskName;
	@NotBlank(message = "task Identifier is required")
	@Size(min = 4, max = 5, message = "please use 4 to  5 characters")
	private String taskIdentifier;
	@NotBlank(message = "please provide description")
	private String description;
	@NotBlank
	private String projectIdentifier;
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public ProjectTask() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskIdentifier() {
		return taskIdentifier;
	}
	public void setTaskIdentifier(String taskIdentifier) {
		this.taskIdentifier = taskIdentifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start_date;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end_date;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
    @PrePersist
	public void onCreate() {
		 this.createdAt=new Date();
	}
    @PreUpdate
    public void onUpdate() {
    	this.updatedAt=new Date();
    }
}
