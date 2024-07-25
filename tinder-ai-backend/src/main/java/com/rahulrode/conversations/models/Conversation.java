package com.rahulrode.conversations.models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;

/**
 * An entity representing a Conversation, comprised of several
 * {@link ChatMessage} instances
 */
@Builder
@MongoEntity()
public record Conversation(
    @BsonId String id,
    String profileId,
    List<ChatMessage> messages

) {

}
