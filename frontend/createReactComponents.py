import os

# Define the directory to create components in
components_dir = 'src/components'

# Create the components directory if it does not exist
os.makedirs(components_dir, exist_ok=True)

# Define component templates with updated fetch URLs to use port 5173
components = {
    'CreateUserComponent.jsx': '''import React, { useState } from 'react';

const CreateUserComponent = () => {
  const [username, setUsername] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('http://localhost:5173/api/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username }),
    })
    .then(response => response.json())
    .then(data => {
      setMessage('User created successfully');
      setError('');
      setUsername(''); // Clear the input field
    })
    .catch(error => {
      setError('Failed to create user');
      setMessage('');
    });
  };

  return (
    <div className="create-user">
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input 
            type="text" 
            value={username} 
            onChange={(e) => setUsername(e.target.value)} 
          />
        </label>
        <button type="submit">Create User</button>
      </form>
      {message && <p className="message">{message}</p>}
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default CreateUserComponent;
''',
    'CreatePollComponent.jsx': '''import React, { useState } from 'react';

const CreatePollComponent = () => {
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
    fetch('http://localhost:5173/api/polls', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ question, options }),
    })
    .then(response => response.json())
    .then(data => {
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
''',
    'VoteComponent.jsx': '''import React, { useState, useEffect } from 'react';

const VoteComponent = () => {
  const [polls, setPolls] = useState([]);
  const [selectedPoll, setSelectedPoll] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetch('http://localhost:5173/api/polls')
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
'''
}

# Create each component file with the respective content
for filename, content in components.items():
    file_path = os.path.join(components_dir, filename)
    with open(file_path, 'w') as file:
        file.write(content)

print("Components created successfully.")
