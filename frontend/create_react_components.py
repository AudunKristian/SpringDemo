import os

# Define the directory to create components in
components_dir = 'src/components'

# Create the components directory if it does not exist
os.makedirs(components_dir, exist_ok=True)

# Define component templates
components = {
    'CreateUserComponent.jsx': '''import React, { useState } from 'react';

const CreateUserComponent = () => {
  const [username, setUsername] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('/api/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('User created:', data);
    })
    .catch(error => {
      console.error('Error:', error);
    });
  };

  return (
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
  );
};

export default CreateUserComponent;
''',
    'CreatePollComponent.jsx': '''import React, { useState } from 'react';

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
''',
    'VoteComponent.jsx': '''import React, { useState, useEffect } from 'react';

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
'''
}

# Create each component file with the respective content
for filename, content in components.items():
    file_path = os.path.join(components_dir, filename)
    with open(file_path, 'w') as file:
        file.write(content)

print("Components created successfully.")
