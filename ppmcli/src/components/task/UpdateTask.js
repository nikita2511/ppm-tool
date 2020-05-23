import React, { Component } from 'react';

class UpdateTask extends Component {
  constructor(){
           super();
           this.state={
                  taskName:'',
                  taskIdentifier:""
           }
           this.onChange=this.onChange.bind(this);
           this.onSubmit=this.onSubmit.bind(this);
  }
      onChange(){
      
      }
      onSubmit(){

      }
  
    render() {
        return (
            <div>
                
            </div>
        );
    }
}

export default UpdateTask;