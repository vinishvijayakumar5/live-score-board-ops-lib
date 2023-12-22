package com.zyxcorp.libs.scoreboard.dto;

import lombok.NonNull;

public record MatchDto(
        @NonNull()
        ScoreDto homeTeam,
        @NonNull
        ScoreDto awayTeam
) {}
