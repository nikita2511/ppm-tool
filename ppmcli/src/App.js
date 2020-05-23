import logo from "./logo.svg";
import "./App.css";

import React, { Component } from "react";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/projects/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/projects/UpdateProject";
import ProjectBoard from "./components/ProjectBoard";
import AddTask from "./components/task/AddTask";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Route path="/dashboard" component={Dashboard} />
          <Route path="/addProject" component={AddProject} />
          <Route path="/updateProject/:id" component={UpdateProject} />
          <Route path="/taskdashboard/:id" component={ProjectBoard} />
          <Route path="/addtask" component={AddTask} />
          <Route path="/addProjectTask" component={AddTask}/>
         
        </Router>
      </Provider>
    );
  }
}

export default App;