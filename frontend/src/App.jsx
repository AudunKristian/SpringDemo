import React from 'react';
import CreateUserComponent from './components/CreateUserComponent';
import CreatePollComponent from './components/CreatePollComponent';
import VoteComponent from './components/VoteComponent';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Poll Application</h1>
        <CreateUserComponent />
        <CreatePollComponent />
        <VoteComponent />
      </header>
    </div>
  );
}

export default App;
