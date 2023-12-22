package com.zyxcorp.libs.scoreboard;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.InvalidScoreException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import com.zyxcorp.libs.scoreboard.exception.MatchNotFoundException;

import java.util.List;

public interface MatchScoreboard {

    int create(MatchDto dto) throws MatchExistsException, InvalidMatchException;
    void delete(int id);
    MatchDto get(int id) throws MatchNotFoundException;
    MatchDto update(int id, int homeTeamScore, int awayTeamScore) throws MatchNotFoundException, InvalidScoreException;
    List<MatchDto> get() throws MatchNotFoundException;
}
