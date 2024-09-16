import React, { useState, useEffect } from 'react';

const VoteComponent = () => {
  const [polls, setPolls] = useState([]);
  const [selectedPoll, setSelectedPoll] = useState('');
  const [selectedOption, setSelectedOption] = useState('');

  useEffect(() => {
    fetch('/api/polls')
      .then(response => response.json())
      .then(data => setPolls(data))
      .catch(error => console.error('Error:', error));
  }, []);

  const handleVote = (event) => {
    event.preventDefault();
    fetch(`/api/polls/${selectedPoll}/vote`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ option: selectedOption }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Vote recorded:', data);
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  return (
    <form onSubmit={handleVote}>
      <label>
        Select Poll:
        <select 
          value={selectedPoll} 
          onChange={(e) => setSelectedPoll(e.target.value)}
        >
          {polls.map(poll => (
            <option key={poll.id} value={poll.id}>{poll.question}</option>
          ))}
        </select>
      </label>
      <label>
        Select Option:
        <select 
          value={selectedOption} 
          onChange={(e) => setSelectedOption(e.target.value)}
        >
          {polls.find(poll => poll.id === selectedPoll)?.options.map((option, index) => (
            <option key={index} value={option}>{option}</option>
          ))}
        </select>
      </label>
      <button type="submit">Vote</button>
    </form>
  );
};

export default VoteComponent;
