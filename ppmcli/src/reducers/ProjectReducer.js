import { GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "../actions/type";
import {GET_TASKS,GET_TASK,DELETE_TASK} from "../actions/type";
const initialState = {
  projects: [],
  tasks: [],
  task: {},
  project: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_PROJECTS:
      return {
        ...state,
        projects: action.payload
      };
    case GET_PROJECT:
      return {
        ...state,
        project: action.payload
      };

    case DELETE_PROJECT:
      return {
        ...state,
        projects: state.projects.filter(
          project => project.projectIdentifier != action.payload
        )
      };
      case GET_TASKS:
        return {
          ...state,
          tasks: action.payload
        };
      case GET_TASK:
        return {
          ...state,
          task: action.payload
        };
  
      case DELETE_TASK:
        return {
          ...state,
          tasks: state.tasks.filter(
            task => task.taskIdentifier != action.payload
          )
        };

    default:
      return state;
  }
}