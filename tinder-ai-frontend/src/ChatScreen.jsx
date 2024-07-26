import { User, Send} from "lucide-react";
import { useState } from "react";
/**
 *
 * @returns
 */
export function ChatScreen({ currentMatch, conversation, onSend }) {
  const [input, setInput] = useState("");

  const handleSend = () => {
    if (input.trim()) {
      onSend(conversation.id, input);
      setInput("");
    }
    // refreshState();
  };

  return currentMatch ? (
    <div className='rounded-lg shadow-lg p-4'>
      <h2 className="text-2xl font-bold mb-4">Chat with {currentMatch.firstName} {currentMatch.lastName} </h2>
      <div className="h-[50vh] border rounded-lg overflow-y-auto mb-6 p-4 bg-gray-50">
        {conversation.messages.map((message, index) => (
          <div key={index} className={`flex ${message.authorId === '1' ? 'justify-end' : 'justify-start'} mb-4`}>
            <div className={`flex items-end ${message.authorId === '1' ? 'flex-row-reverse' : 'flex-row'}`}>
              {message.authorId === '1' ? (<User className="w-5 h-5 rounded-full" />) : 
              (<img
                src={`http://localhost:8080/images/${currentMatch.imageUrl}`}
                className="w-8 h-8 rounded-full"
              />)}
              <div
                className={`max-w-xs px-4 py-2 rounded-2xl ${
                  message.authorId === '1'
                    ? 'bg-blue-500 text-white ml-2'
                    : 'bg-gray-200 text-gray-800 mr-2'
                }`}
              >
                {message.messageText}
              </div>
            </div>
          </div>
        ))}
      </div>
      <div className="flex items-center">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          className="flex-1 border-2 border-gray-300 rounded-full py-2 px-4 mr-2 focus:outline-none focus:border-blue-500"
          placeholder="Type a message..."
        />
        <button
          className="bg-blue-500 text-white rounded-full p-2 hover:bg-blue-600 transition-colors duration-200"
          onClick={handleSend}
        >
          <Send size={24} />
        </button>
      </div>
      </div>
  ) : (<div>Loading...</div>);
}
