package com.rahulrode.matches.models;

import org.bson.codecs.pojo.annotations.BsonId;

import com.rahulrode.profiles.models.Profile;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;

/**
 * An entity representing individual Tinder Match
 */
@Builder
@MongoEntity
public record Match(
    @BsonId String id,
    Profile profile,
    String conversationId) {

}
