import React, { useState } from 'react';
import './css/CreateUserComponent.css';

const CreateUserComponent = () => {
  const [username, setUsername] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    fetch('http://localhost:8080/api/users', {
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
