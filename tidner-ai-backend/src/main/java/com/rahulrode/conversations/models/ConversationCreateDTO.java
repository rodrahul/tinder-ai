package com.rahulrode.conversations.models;

import jakarta.validation.constraints.NotNull;

/**
 * A data model representing a new conversation to create
 */
public record ConversationCreateDTO(
    /**
     * id of the profile with whom the conversation is being created
     */
    @NotNull String profileId

) {

}
