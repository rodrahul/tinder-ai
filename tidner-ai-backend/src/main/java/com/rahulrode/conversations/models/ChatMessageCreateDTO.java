package com.rahulrode.conversations.models;

import jakarta.validation.constraints.NotNull;

public record ChatMessageCreateDTO(
    @NotNull String messageText,
    @NotNull String authorId

) {
}