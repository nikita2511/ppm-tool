import React, { Component } from "react";
import { Link } from "react-router-dom";

import { connect } from "react-redux";
import { PropTypes } from "prop-types";
import { deleteTask } from "../../src/actions/TaskActions";

class TaskItem extends Component {
  onDeleteClick = id => {
    console.log("-------> method called.");
    this.props.deleteTask(id);
  };

  render() {
    const { task} = this.props;

    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{task.taskIdentifier}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h3>{task.taskName}</h3>
              <p>{task.description}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
               <Link to={`/updateTask/${task.taskIdentifier}`}>
                  <li className="list-group-item update">
                    <i className="fa fa-edit pr-1"> Update Task Info</i>
                  </li>
                </Link>

                <li
                  className="list-group-item delete"
                  onClick={this.onDeleteClick.bind(
                    this,
                    task.taskIdentifier
                  )}
                >
                  <i className="fa fa-minus-circle pr-1"> Delete Task</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

TaskItem.propTypes = {
  deleteTask: PropTypes.func.isRequired
};

export default connect(
  null,
  { deleteTask }
)(TaskItem);