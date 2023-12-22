package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.MatchScoreboard;
import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.InvalidScoreException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import com.zyxcorp.libs.scoreboard.exception.MatchNotFoundException;
import com.zyxcorp.libs.scoreboard.mapper.MatchScoreBoardMapper;
import com.zyxcorp.libs.scoreboard.repository.MatchScoreBoardRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class MatchScoreBoardService implements MatchScoreboard {

    private MatchScoreBoardRepository repository;
    private MatchScoreBoardMapper mapper;

    @Override
    public int create(@Valid MatchDto dto) throws MatchExistsException, InvalidMatchException {
        newMatchAcceptance(dto);
        return getRepository().insert(getMapper().map(dto));
    }

    @Override
    public void delete(int id) {
        getRepository().deleteById(id);
    }

    @Override
    public MatchDto get(int id) throws MatchNotFoundException {
        MatchEntity entity = getRepository().findById(id);
        if(nonNull(entity)) {
            return getMapper().map(entity);
        }
        throw new MatchNotFoundException("Match not found", "E103");
    }

    @Override
    public MatchDto update(int id, int homeTeamScore, int awayTeamScore) throws MatchNotFoundException, InvalidScoreException {
        updateMatchAcceptance(id, homeTeamScore, awayTeamScore);
        return getMapper().map(getRepository().update(id, homeTeamScore, awayTeamScore));
    }

    @Override
    public List<MatchDto> get() throws MatchNotFoundException {
        return null;
    }

    private void updateMatchAcceptance(int id, int homeTeamScore, int awayTeamScore) throws MatchNotFoundException, InvalidScoreException {
        MatchEntity entity = getRepository().findById(id);
        if(isNull(entity)) {
            throw new MatchNotFoundException("Match not found", "E103");
        }
        validateScore(entity, homeTeamScore, awayTeamScore);
    }

    private void validateScore(MatchEntity entity, int homeTeamScore, int awayTeamScore) throws InvalidScoreException {
        if(homeTeamScore < entity.getHomeTeamPoints()) {
            throw new InvalidScoreException("Invalid home team score", "E104");
        } else if(awayTeamScore < entity.getAwayTeamPoints()) {
            throw new InvalidScoreException("Invalid away team score", "E105");
        }
    }

    private void newMatchAcceptance(MatchDto dto) throws MatchExistsException, InvalidMatchException {
        try {
            boolean exists = getRepository().isExists(dto.homeTeam().name(), dto.awayTeam().name());
            if(exists) {
                throw new MatchExistsException("Match exists", "E100");
            }
        } catch (MatchExistsException exception) {
            throw exception;
        } catch (NullPointerException exception) {
            throw new InvalidMatchException("Invalid match", "E101");
        }
    };

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
