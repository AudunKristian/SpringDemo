import React, { useState, useEffect } from 'react';
import CreateUserComponent from './components/CreateUserComponent';
import CreatePollComponent from './components/CreatePollComponent';
import VoteComponent from './components/VoteComponent';
import './App.css';

function App() {
  const [polls, setPolls] = useState([]);
  const [users, setUsers] = useState([]);

  // Fetch polls initially
  useEffect(() => {
    fetch('http://localhost:8080/api/polls')
      .then(response => response.json())
      .then(data => setPolls(data))
      .catch(error => console.error('Failed to fetch polls:', error));
  }, []);

  const addPoll = (newPoll) => {
    setPolls(prevPolls => [...prevPolls, newPoll]);
  };

  const addUser = (newUser) => {
    setUsers(prevUsers => [...prevUsers, newUser]);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Poll Application</h1>
        <CreateUserComponent addUser={addUser} />
        <CreatePollComponent addPoll={addPoll} />
        <VoteComponent polls={polls} />
      </header>
    </div>
  );
}

export default App;
