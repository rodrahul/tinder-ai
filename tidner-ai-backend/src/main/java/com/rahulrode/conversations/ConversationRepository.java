package com.rahulrode.conversations;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConversationRepository implements PanacheMongoRepositoryBase<Conversation, String>{

}
