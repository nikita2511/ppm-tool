
import { Link } from "react-router-dom";
import React from "react";
const CreateTaskButton =() =>{
      return(
          
              <React.Fragment>
              <Link to="/addProjectTask" className="btn btn-lg btn-info">
              Create a Task
            </Link>
              </React.Fragment>
          
      )
}
export default CreateTaskButton;