import { useState } from 'react';
import './App.css'
import { User, MessageCircle, X, Heart } from 'lucide-react'

/**
 * Profile selector
 * @returns 
 */
const ProfileSelector = () => (
  <div className='rounded-lg overflow-hidden bg-white shadow-lg'>
    <div className='relative'>
      <img src='http://localhost:8080/images/0e985a4b-6944-4dec-b0f7-4a4453592dd0.jpg' alt='' />
      <div className='absolute bottom-0 left-0 right-0 text-white p-4 bg-gradient-to-t from-black'>
        <h2 className='text-3xl font-bold'>Foo Bar, 30</h2>
      </div>
    </div>

    <div className='p-4'>
      <p className='text-gray-600 mb-'>I'm a sf engine with aglhlasd</p>
    </div>

    {/* Swipe Buttons */}
    <div className='flex justify-center space-x-4 p-4'>
      <button className='bg-red-500 rounded-full p-4 text-white hover:bg-red-700'
        onClick={() => console.log("swipe left")}>
        <X size={24} />
      </button>
      <button className='bg-green-500 rounded-full p-4 text-white hover:bg-green-700'
        onClick={() => console.log("swipe right")}>
        <Heart size={24} />
      </button>
    </div>
  </div>
)

/**
 * 
 * @returns 
 */
const MatchesList = ({ onSelectMatch }) => (
  <div className='rounded-lg shadow-lg p-4'>
    <h2 className='text-2xl font-bold mb-4'>Matches</h2>
    <ul >

      {[
        { id: 1, firstName: 'Foo', lastName: 'bar', age: 33, imageUrl: 'http://localhost:8080/images/0e985a4b-6944-4dec-b0f7-4a4453592dd0.jpg' },
        { id: 2, firstName: 'this', lastName: 'that', age: 37, imageUrl: 'http://localhost:8080/images/0f242a88-609b-4329-b96d-e0486cd2c371.jpg' },
      ].map(match => (
        <li key={match.id} className='mb-2'>
          <button
            className='flex w-full hover:bg-gray-100 rounded items-center'
            onClick={onSelectMatch}
          >
            <img src={match.imageUrl} alt="" className='size-20 mr-4 rounded-full' />
            <span>
              <h3 className='font-bold'>{match.firstName} {match.lastName}</h3>
            </span>
          </button>
        </li>
      ))}
    </ul>
  </div>
);


const ChatScreen = () => {
  const [input, setInput] = useState('');

  const handleSend = () => {
    if (input.trim()) {
      console.log(input);
      setInput('');
    }
  }

  return (
    <div className='rounded-lg shadow-lg p-4'>
      <h2 className='text-2xl font-bold mb-4'>Chat with Foo Bar</h2>
      <div className='border rounded overflow-y-auto mb-4 p-4 h-[50vh]'>
        {[
          "Hi",
          "How are you??"
        ].map((message, index) => (
          <div key={index}>
            <div className='mb-4 p-2 rounded bg-gray-100'>{message} </div>
          </div>
        ))
        }
      </div>

      {/* Chat input */}
      <div className='flex'>
        <input
          type='text'
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className='flex-1 rounded p-2  mr-2 border'
          placeholder='Type a message...'
        />
        <button
          className='bg-blue-500 text-white rounded p-2'
          onClick={handleSend}
        >Send</button>
      </div>
    </div>
  )

}

function App() {

  const [currentScreen, setCurrentScreen] = useState('profile') // Default value of current screen is profile

  const renderScreen = () => {
    switch (currentScreen) {
      case 'profile':
        return <ProfileSelector />
      case 'matches':
        return <MatchesList onSelectMatch={() => setCurrentScreen('chat')} />
      case 'chat':
        return <ChatScreen />
    }
  }

  return (

    <div className='max-w-md mx-auto p-4'>
      <nav className='flex justify-between'>
        <User onClick={() => setCurrentScreen('profile')} />
        <MessageCircle onClick={() => setCurrentScreen('matches')} />
      </nav>
      {renderScreen()}
    </div>

  )
}

export default App
