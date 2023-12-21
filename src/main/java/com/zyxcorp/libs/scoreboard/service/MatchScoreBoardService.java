package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.MatchScoreboard;
import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.mapper.MatchScoreBoardMapper;
import com.zyxcorp.libs.scoreboard.repository.MatchScoreBoardRepository;
import lombok.AllArgsConstructor;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class MatchScoreBoardService implements MatchScoreboard {

    private MatchScoreBoardRepository repository;
    private MatchScoreBoardMapper mapper;

    @Override
    public int create(MatchDto dto) {
        return getRepository().insert(getMapper().map(dto));
    }

    private MatchScoreBoardMapper getMapper() {
        return nonNull(mapper) ? mapper : new MatchScoreBoardMapper();
    }

    private MatchScoreBoardRepository getRepository() {
        return nonNull(repository) ? repository : new MatchScoreBoardRepository();
    }
}
