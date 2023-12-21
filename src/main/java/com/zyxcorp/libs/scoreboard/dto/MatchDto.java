package com.zyxcorp.libs.scoreboard.dto;

public record MatchDto(
        ScoreDto homeTeam,
        ScoreDto awayTeam
) {}
