package com.rahulrode.conversations;

import java.time.LocalDateTime;

import com.rahulrode.conversations.models.ChatMessage;
import com.rahulrode.conversations.models.ChatMessageCreateDTO;
import com.rahulrode.conversations.models.Conversation;
import com.rahulrode.exception.ResponseStatusException;
import com.rahulrode.profiles.ProfileRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RequestScoped
public class ConversationService {

  @Inject
  Validator validator;

  // DB Manager
  @Inject
  ProfileRepository profileRepo;
  @Inject
  ConversationRepository conversationRepo;

  /**
   * 
   * @param conversationId
   * @return
   */
  public Conversation getConversation(String conversationId) {
    var conversation = conversationRepo
        .findByIdOptional(conversationId)
        .orElseThrow(() -> new ResponseStatusException(404, "Conversation not found"));

    return conversation;
  }

  /**
   * 
   * @param conversationId
   * @param chatMessage
   * @return
   */
  public Conversation addMessageToConversation(String conversationId, @Valid ChatMessageCreateDTO chatMessage) {

    var conversation = conversationRepo
        .findByIdOptional(conversationId)
        .orElseThrow(() -> new ResponseStatusException(404, "Conversation not found"));

    profileRepo.findByIdOptional(chatMessage.authorId())
        .orElseThrow(() -> new ResponseStatusException(404, "Author not found for id: " + chatMessage.authorId()));

    // TODO: need to validate that the author of the message happens to be only the
    // profile assocated with message user
    conversation.messages()
        .add(new ChatMessage(chatMessage.messageText(), chatMessage.authorId(), LocalDateTime.now()));

    conversationRepo.update(conversation);
    return conversation;
  }
}