package com.zyxcorp.libs.scoreboard.entitiy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class MatchEntity {

    private int id;

    private String homeTeamName;
    private int homeTeamPoints;

    private String awayTeamName;
    private int awayTeamPoints;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
