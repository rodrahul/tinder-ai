package com.rahulrode.conversations;

import org.jboss.resteasy.reactive.RestPath;

import com.rahulrode.conversations.models.ChatMessageCreateDTO;
import com.rahulrode.conversations.models.Conversation;
import com.rahulrode.conversations.models.ConversationCreateDTO;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.UriInfo;

@Path("/conversations")
public class ConversationResource {

  @Inject
  ConversationService conversationService;

  @Inject
  UriInfo uriInfo;

  @POST
  public Conversation createNewConversation(ConversationCreateDTO conversation) {
    Log.info(uriInfo.getPath());
    return conversationService.createConversation(conversation);
  }

  @POST
  @Path("/{conversationId}")
  public Conversation addMessageToConversation(@RestPath String conversationId, ChatMessageCreateDTO chatMessage) {
    return conversationService.addMessageToConversation(conversationId, chatMessage);
  }

}
