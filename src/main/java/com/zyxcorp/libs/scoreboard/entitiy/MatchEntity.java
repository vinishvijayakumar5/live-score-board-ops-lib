package com.zyxcorp.libs.scoreboard.entitiy;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class MatchEntity {

    private int id;

    private String homeTeamName;
    private int homeTeamPoints;

    private String awayTeamName;
    private int awayTeamPoints;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
