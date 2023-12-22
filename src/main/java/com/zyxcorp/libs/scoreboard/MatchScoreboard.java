package com.zyxcorp.libs.scoreboard;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;

public interface MatchScoreboard {

    int create(MatchDto dto);
    void delete(int id);
    MatchDto get(int id);

}
