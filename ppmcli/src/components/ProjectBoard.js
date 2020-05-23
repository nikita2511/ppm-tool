import React, { Component } from 'react';
import CreateTaskButton from './projects/CreateTaskButton';
import TaskItem from './TaskItem';

import { connect } from "react-redux";
import {getTasks} from   "../actions/TaskActions";
import { PropTypes } from "prop-types";

class ProjectBoard extends Component {
    componentDidMount() {
        this.props.getTasks();
      }
    render() {
        const {tasks } = this.props.tasks;
      
        return (
       <div className="projects">
     <div className="container">
     <div className="row">
    <div className="col-md-12">
        <h1 className="display-4 text-center">Project Name</h1>
      <br />
    
      <CreateTaskButton />
      <br />
      <hr />
      {tasks.map(task => (
        <TaskItem key={task.id} task={task} />
      ))}
    </div>
  </div>
</div>
</div>
);
}
}
ProjectBoard.propTypes = {
task: PropTypes.object.isRequired,
getTasks: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
tasks: state.tasks
});

export default connect(
mapStateToProps,
{ getTasks }
)(ProjectBoard);