package com.rahulrode.profiles.models;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;

/**
 * An entity representing individual Tinder Profile
 */
@Builder
@MongoEntity
public record Profile(
    @BsonId String id,
    String firstName,
    String lastName,
    int age,
    String ethnicity,
    Gender gender,
    String bio,
    String imageUrl,
    String myersBriggsPersonalityType) {
}