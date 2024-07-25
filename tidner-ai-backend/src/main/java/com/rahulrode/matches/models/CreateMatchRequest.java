package com.rahulrode.matches.models;

import jakarta.validation.constraints.NotNull;

public record CreateMatchRequest(

    @NotNull String profileId

) {

}
