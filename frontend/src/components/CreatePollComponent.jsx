import React, { useState } from 'react';
import './css/CreatePollComponent.css';

const CreatePollComponent = ({ addPoll }) => {
  const [question, setQuestion] = useState('');
  const [options, setOptions] = useState(['']);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const addOption = () => setOptions([...options, '']);

  const handleOptionChange = (index, value) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('http://localhost:8080/api/polls', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ question, options }),
    })
    .then(response => response.json())
    .then(data => {
      addPoll(data); // Add the new poll to the state
      setMessage('Poll created successfully');
      setError('');
      setQuestion('');
      setOptions(['']); // Clear options
    })
    .catch(error => {
      setError('Failed to create poll');
      setMessage('');
    });
  };

  return (
    <div className="create-poll">
      <form onSubmit={handleSubmit}>
        <label>
          Poll Question:
          <input 
            type="text" 
            value={question} 
            onChange={(e) => setQuestion(e.target.value)} 
          />
        </label>
        {options.map((option, index) => (
          <label key={index}>
            Option {index + 1}:
            <input 
              type="text" 
              value={option} 
              onChange={(e) => handleOptionChange(index, e.target.value)} 
            />
          </label>
        ))}
        <button type="button" onClick={addOption}>Add Option</button>
        <button type="submit">Create Poll</button>
      </form>
      {message && <p className="message">{message}</p>}
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default CreatePollComponent;
