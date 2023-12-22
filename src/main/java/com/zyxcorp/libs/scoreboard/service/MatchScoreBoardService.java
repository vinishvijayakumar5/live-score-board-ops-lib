package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.MatchScoreboard;
import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import com.zyxcorp.libs.scoreboard.exception.MatchNotFoundException;
import com.zyxcorp.libs.scoreboard.mapper.MatchScoreBoardMapper;
import com.zyxcorp.libs.scoreboard.repository.MatchScoreBoardRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.function.Consumer;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class MatchScoreBoardService implements MatchScoreboard {

    private MatchScoreBoardRepository repository;
    private MatchScoreBoardMapper mapper;

    @Override
    public int create(@Valid MatchDto dto) {
        CREATE_MATCH.accept(dto);
        return getRepository().insert(getMapper().map(dto));
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public MatchDto get(int id) {
        return null;
    }

    private final Consumer<MatchDto> CREATE_MATCH = (dto -> {
        try {
            boolean exists = getRepository().isExists(dto.homeTeam().name(), dto.awayTeam().name());
            if(exists) {
                throw new MatchExistsException("Match exists", "E100");
            }
        } catch (NullPointerException exception) {
            throw new InvalidMatchException("Invalid match", "E101");
        }
    });

    private MatchScoreBoardMapper getMapper() {
        if(nonNull(mapper)) {
            return mapper;
        }
        mapper = new MatchScoreBoardMapper();
        return mapper;
    }

    private MatchScoreBoardRepository getRepository() {
        if(nonNull(repository)) {
            return repository;
        }
        repository = new MatchScoreBoardRepository();
        return repository;
    }
}
