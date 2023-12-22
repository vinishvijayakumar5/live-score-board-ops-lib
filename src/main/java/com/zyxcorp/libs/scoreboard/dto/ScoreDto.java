package com.zyxcorp.libs.scoreboard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ScoreDto(
        @NotEmpty
        String name,

        @Min(0)
        int points
){}
