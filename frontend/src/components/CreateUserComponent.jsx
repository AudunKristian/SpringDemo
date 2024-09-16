import React, { useState } from 'react';

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
