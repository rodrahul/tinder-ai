import { Heart, MessageCircle, User, X } from "lucide-react";
import { useEffect, useState } from "react";
import "./App.css";
import { MatchesList } from "./MatchesList";
import { ChatScreen } from "./ChatScreen";

const host = "http://localhost:8080/";

const fetchRandomProfile = async () => {
  var endpoint = host + "profiles/random";
  const response = await fetch(endpoint);

  if (!response.ok) throw new Error("Failed to fetch random profile");

  return response.json();
};

const saveMatch = async (profileId) => {
  var endpoint = host + "matches";
  const response = await fetch(endpoint, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ profileId }),
  });

  if (!response.ok) throw new Error("Failed to fetch random profile");

  return response.json();
};

const fetchMatches = async () => {
  var endpoint = host + "matches";
  const response = await fetch(endpoint);

  if (!response.ok) throw new Error("Failed to fetch Matches");

  return response.json();
};

const fetchConversation = async (conversationId) => {
  var endpoint = host + "conversations/" + conversationId;
  const response = await fetch(endpoint);

  if (!response.ok) throw new Error("Failed to fetch Conversation");

  return response.json();
};

const sendMessage = async (conversationId, messageText) => {
  // When the user is sending a messagge the authorId is always 1
  var endpoint = host + "conversations/" + conversationId;
  var chatMessage = {
    messageText: messageText,
    authorId: 1,
  };
  const response = await fetch(endpoint, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(chatMessage),
  });

  if (!response.ok) throw new Error("Failed to send chat message");

  return response.json();
};
/**
 * Profile selector
 * @returns
 */
const ProfileSelector = ({ profile, onSwipe }) =>
  profile ? (
    <div className="rounded-lg overflow-hidden bg-white shadow-lg">
      <div className="relative">
        <img src={host + "/images/" + profile.imageUrl} alt="" />
        <div className="absolute bottom-0 left-0 right-0 text-white p-4 bg-gradient-to-t from-black">
          <h2 className="text-3xl font-bold">
            {profile.firstName} {profile.lastName}, {profile.age}
          </h2>
        </div>
      </div>

      <div className="p-4">
        <p className="text-gray-600 mb-">{profile.bio}</p>
      </div>

      {/* Swipe Buttons */}
      <div className="flex justify-center space-x-4 p-4">
        <button
          className="bg-red-500 rounded-full p-4 text-white hover:bg-red-700"
          onClick={() => onSwipe(profile.id, "left")}
        >
          <X size={24} />
        </button>
        <button
          className="bg-green-500 rounded-full p-4 text-white hover:bg-green-700"
          onClick={() => onSwipe(profile.id, "right")}
        >
          <Heart size={24} />
        </button>
      </div>
    </div>
  ) : (
    <div> Loading ...</div>
  );

/**
 *
 * @returns
 */
function App() {
  const loadRandomProfile = async () => {
    try {
      const profile = await fetchRandomProfile();
      setCurrentProfile(profile);
    } catch (error) {
      console.error(error);
    }
  };

  const loadMatches = async () => {
    try {
      const matches = await fetchMatches();
      setMatches(matches);
    } catch (error) {
      console.error(error);
    }
  };

  // side effect to call the loadRandomProfile when App() is loaded
  useEffect(() => {
    loadRandomProfile();
    loadMatches();
  }, {});

  const [currentScreen, setCurrentScreen] = useState("profile"); // Default value of current screen is profile
  const [currentProfile, setCurrentProfile] = useState(null);
  const [matches, setMatches] = useState([]);
  const [currentMatchAndConversation, setCurrentMatchAndConversation] =
    useState({ match: {}, conversation: {} });

  const onSwipe = async (profileId, direction) => {
    // We've to load next random profile either liked or disliked current profile
    loadRandomProfile();
    if (direction === "right") {
      // Register a Match with the backed
      console.log("liked");
      await saveMatch(profileId);
      await loadMatches();
    } else {
      console.log("disliked");
    }
  };

  const onSelectMatch = async (profile, conversationId) => {
    console.log("selected match ", profile.firstName);
    const conversation = await fetchConversation(conversationId);
    setCurrentMatchAndConversation({
      match: profile,
      conversation: conversation,
    });
    setCurrentScreen("chat");
  };

  // const refreshChatState = async () => {
  //   const conv = await fetchConversation(
  //     currentMatchAndConversation.conversation.id
  //   );
  //   setCurrentMatchAndConversation({
  //     match: currentMatchAndConversation.match,
  //     conversation: conv,
  //   });
  //   console.log(currentMatchAndConversation);
  // };
  const onChatMessageSend = async (conversationId, message) => {
    const conv = await sendMessage(conversationId, message);
    setCurrentMatchAndConversation({
      match: currentMatchAndConversation.match,
      conversation: conv,
    });
  };

  const renderScreen = () => {
    switch (currentScreen) {
      case "profile":
        return <ProfileSelector profile={currentProfile} onSwipe={onSwipe} />;

      case "matches":
        // loadMatches();
        return <MatchesList matches={matches} onSelectMatch={onSelectMatch} />;

      case "chat":
        return (
          <ChatScreen
            currentMatch={currentMatchAndConversation.match}
            conversation={currentMatchAndConversation.conversation}
            onSend={onChatMessageSend}
          />
        );
    }
  };

  return (
    <div className="max-w-md mx-auto p-4">
      <nav className="flex justify-between">
        <User onClick={() => setCurrentScreen("profile")} />
        <MessageCircle onClick={() => setCurrentScreen("matches")} />
      </nav>
      {renderScreen()}
    </div>
  );
}

export default App;
