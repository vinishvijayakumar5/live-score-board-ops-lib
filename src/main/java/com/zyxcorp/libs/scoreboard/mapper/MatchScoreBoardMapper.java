package com.zyxcorp.libs.scoreboard.mapper;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;

public class MatchScoreBoardMapper {

    public MatchEntity map(MatchDto dto) {
        return MatchEntity.builder()
                .homeTeamName(dto.homeTeam().name())
                .homeTeamPoints(dto.homeTeam().points())
                .awayTeamName(dto.awayTeam().name())
                .awayTeamPoints(dto.awayTeam().points())
                .build();
    }
}
