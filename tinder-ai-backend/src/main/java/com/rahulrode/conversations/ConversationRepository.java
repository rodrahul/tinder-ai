package com.rahulrode.conversations;

import com.rahulrode.conversations.models.Conversation;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConversationRepository implements PanacheMongoRepositoryBase<Conversation, String>{

}
