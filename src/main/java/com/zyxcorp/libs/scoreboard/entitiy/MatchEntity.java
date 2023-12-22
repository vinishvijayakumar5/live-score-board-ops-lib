package com.zyxcorp.libs.scoreboard.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    private int id;

    private String homeTeamName;
    private int homeTeamPoints;

    private String awayTeamName;
    private int awayTeamPoints;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public int getTotalPoints() {
        return homeTeamPoints + awayTeamPoints;
    }
}
