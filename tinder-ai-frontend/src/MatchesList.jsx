const host = "http://localhost:8080/";

export function MatchesList({ matches, onSelectMatch }) {
  return (
    <div className="rounded-lg shadow-lg p-4">
      <h2 className="text-2xl font-bold mb-4">Matches</h2>
      <ul>
        {matches.map((match, index) => (
          <li key={index} className="mb-2">
            <button
              className="flex w-full hover:bg-gray-100 rounded items-center"
              onClick={() => onSelectMatch(match.profile, match.conversationId)}
            >
              <img
                src={host + "/images/" + match.profile.imageUrl}
                alt=""
                className="size-20 mr-4 rounded-full"
              />
              <span>
                <h3 className="font-bold">
                  {match.profile.firstName} {match.profile.lastName}
                </h3>
              </span>
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
