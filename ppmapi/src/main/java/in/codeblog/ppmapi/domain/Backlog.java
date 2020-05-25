package in.codeblog.ppmapi.domain;


@Entity
public class Backlog {
	
	
//one to many relationship with projecct Task
	@OnetoMany(fetch=fetchType.Eager,cascade=CascadeType.ALL ,mappedBy="backlog")

}
