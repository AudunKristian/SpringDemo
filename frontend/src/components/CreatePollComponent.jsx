import React, { useState } from 'react';
import './css/createPollComponent.css';

const CreatePollComponent = () => {
  const [question, setQuestion] = useState('');
  const [options, setOptions] = useState(['']);

  const addOption = () => setOptions([...options, '']);

  const handleOptionChange = (index, value) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('/api/polls', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ question, options }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Poll created:', data);
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  return (
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
  );
};

export default CreatePollComponent;
