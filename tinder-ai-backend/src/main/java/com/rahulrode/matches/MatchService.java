package com.rahulrode.matches;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.rahulrode.conversations.ConversationRepository;
import com.rahulrode.conversations.models.Conversation;
import com.rahulrode.exception.ResponseStatusException;
import com.rahulrode.matches.models.CreateMatchRequest;
import com.rahulrode.matches.models.Match;
import com.rahulrode.profiles.ProfileRepository;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@Dependent
public class MatchService {

  // DB Manager
  @Inject
  ProfileRepository profileRepo;
  @Inject
  ConversationRepository conversationRepo;
  @Inject
  MatchRepository matchRepo;

  /**
   * 
   * @param matchRequest
   * @return
   */
  public Match createMatch(@Valid CreateMatchRequest matchRequest) {

    var profileToMatch = profileRepo.findByIdOptional(matchRequest.profileId())
        .orElseThrow(() -> new ResponseStatusException(404, "Profile Not found for id: " + matchRequest.profileId()));

    // Create a conversation with the match if it does not exist
    var conversation = Conversation.builder()
        .id(UUID.randomUUID().toString())
        .profileId(profileToMatch.id())
        .messages(Collections.emptyList())
        .build();
    conversationRepo.persist(conversation);
    var match = Match.builder()
        .id(UUID.randomUUID().toString())
        .profile(profileToMatch)
        .conversationId(conversation.id())
        .build();
    matchRepo.persist(match);

    return match;

  }

  /**
   * 
   * @return
   */
  public List<Match> getAllMatches() {
    return matchRepo.listAll();
  }

}
