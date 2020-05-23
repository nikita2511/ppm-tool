import axios from "axios";
import {GET_ERRORS,GET_TASKS,GET_TASK,DELETE_TASK } from "./type";
export const createTask = (task, history) => async dispatch => {
    try {
      const res = await axios.post("http://localhost:8080/api/project/task", task);
      history.push("/taskDashboard");
    } catch (error) {
      dispatch({
        type: GET_ERRORS,
        payload: error.response.data
      });
    }
  };
  
  export const getTasks = () => async dispatch => {
    const res = await axios.get("http://localhost:8080/api/project/task/all");
    dispatch({
      type: GET_TASKS,
      payload: res.data
    });
  };
  export const getTask = (id, history) => async dispatch => {
    const res = await axios.get(`http://localhost:8080/api/project/task/${id}`);
    dispatch({
      type: GET_TASK,
      payload: res.data
    });
  };
  
  export const deleteTask = id => async dispatch => {
    await axios.delete(`http://localhost:8080/api/project/task/${id}`);
    dispatch({
      type: DELETE_TASK,
      payload: id
    });
  };