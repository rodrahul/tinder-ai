package com.rahulrode.conversations.models;

import java.time.LocalDateTime;

import lombok.Builder;

/**
 * An entity representing a single message which is part of a
 * {@link Conversation}
 */
@Builder
public record ChatMessage(
    String messageText,
    String authorId,
    LocalDateTime messageTime) {
}