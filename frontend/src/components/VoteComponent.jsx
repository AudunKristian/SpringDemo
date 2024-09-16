import React, { useState, useEffect } from 'react';
import './css/VoteComponent.css';

const VoteComponent = () => {
  const [polls, setPolls] = useState([]);
  const [selectedPoll, setSelectedPoll] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetch('http://localhost:8080/api/polls')
      .then(response => response.json())
      .then(data => setPolls(data))
      .catch(error => setError('Failed to fetch polls'));
  }, []);

  const handleVote = (event) => {
    event.preventDefault();
    if (!selectedPoll || !selectedOption) {
      alert('Please select a poll and an option');
      return;
    }
    fetch(`http://localhost:5173/api/polls/${selectedPoll}/vote`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ option: selectedOption }),
    })
    .then(response => response.json())
    .then(data => {
      setMessage('Vote recorded successfully');
      setError('');
    })
    .catch(error => {
      setError('Failed to record vote');
      setMessage('');
    });
  };

  return (
    <div className="vote">
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
      {message && <p className="message">{message}</p>}
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default VoteComponent;
