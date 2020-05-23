    import React, { Component } from 'react';
    import PropTypes from "prop-types";
    import { connect } from "react-redux";
    import { createTask } from "../../actions/TaskActions";
    import classnames from "classnames";
    
class AddTask extends Component {
    constructor(props){
        super(props);
        this.state={
            taskName:"",
            taskIdentifier:"",    
           projectIdentifier: "",
           description: "",
          start_date: "",
          end_date: "",
         errors: {}
        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);
    }
 
    onChange(event) {
        this.setState({ [event.target.name]: event.target.value });
      }
    
      onSubmit(event) {
        event.preventDefault();
        const newTask = {
          taskName: this.state.taskName,
          taskIdentifier: this.state.taskIdentifier,
          projectIdentifier:this.state.projectIdentifier,
          description: this.state.description,
          start_date: this.state.start_date,
          end_date: this.state.end_date
        };
        //console.log(newProject);
        this.props.createTask(newTask, this.props.history);
      }
    
      render() {
        const { errors } = this.state;
    
        return (
          <div className="project">
            <div className="container">
              <div className="row">
                <div className="col-md-8 m-auto">
                  <h5 className="display-4 text-center">Create New Task</h5>
                  <hr />
                  <form onSubmit={this.onSubmit}>
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames("form-control form-control-lg ", {
                          "is-invalid": errors.taskName
                        })}
                        placeholder="Task Name"
                        name="taskName"
                        value={this.state.taskName}
                        onChange={this.onChange}
                      />
                      {errors.taskName && (
                        <div className="invalid-feedback">{errors.taskName}</div>
                      )}
                    </div>
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames("form-control form-control-lg ", {
                          "is-invalid": errors.taskIdentifier
                        })}
                        placeholder="Unique Task ID"
                        name="taskIdentifier"
                        value={this.state.taskIdentifier}
                        onChange={this.onChange}
                      />
                      {errors.taskIdentifier && (
                        <div className="invalid-feedback">
                          {errors.taskIdentifier}
                        </div>
                      )}
                    </div>
                    <div className="form-group">
                      <input
                        type="text"
                        className={classnames("form-control form-control-lg ", {
                          "is-invalid": errors.projectIdentifier
                        })}
                        placeholder="Unique Project ID"
                        name="projectIdentifier"
                        value={this.state.projectIdentifier}
                        onChange={this.onChange}
                      />
                      {errors.projectIdentifier && (
                        <div className="invalid-feedback">
                          {errors.projectIdentifier}
                        </div>
                      )}
                    </div>
      
                    <div className="form-group">
                      <textarea
                        className={classnames("form-control form-control-lg ", {
                          "is-invalid": errors.description
                        })}
                        placeholder="Task Description"
                        name="description"
                        value={this.state.description}
                        onChange={this.onChange}
                      ></textarea>
                      {errors.description && (
                        <div className="invalid-feedback">{errors.description}</div>
                      )}
                    </div>
                    <h6>Start Date</h6>
                    <div className="form-group">
                      <input
                        type="date"
                        className="form-control form-control-lg"
                        name="start_date"
                        value={this.state.start_date}
                        onChange={this.onChange}
                      />
                    </div>
                    <h6>Estimated End Date</h6>
                    <div className="form-group">
                      <input
                        type="date"
                        className="form-control form-control-lg"
                        name="end_date"
                        value={this.state.end_date}
                        onChange={this.onChange}
                      />
                    </div>
    
                    <input
                      type="submit"
                      className="btn btn-primary btn-block mt-4"
                    />
                  </form>
                </div>
              </div>
            </div>
          </div>
        );
      }
    }
    
    AddTask.propTypes = {
      createTask: PropTypes.func.isRequired,
      errors: PropTypes.object.isRequired
    };
    
    const mapStateToProps = state => ({
      errors: state.errors
    });
    
    export default connect(
      mapStateToProps,
      { createTask }
    )(AddTask);