package com.rahulrode.conversations;

import org.jboss.resteasy.reactive.RestPath;

import com.rahulrode.conversations.models.ChatMessageCreateDTO;
import com.rahulrode.conversations.models.Conversation;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/conversations")
public class ConversationResource {

  @Inject
  ConversationService conversationService;

  @GET
  @Path("/{conversationId}")
  public Conversation getConversation(String conversationId) {
    return conversationService.getConversation(conversationId);
  }

  @POST
  @Path("/{conversationId}")
  public Conversation addMessageToConversation(@RestPath String conversationId, ChatMessageCreateDTO chatMessage) {
    return conversationService.addMessageToConversation(conversationId, chatMessage);
  }

}
