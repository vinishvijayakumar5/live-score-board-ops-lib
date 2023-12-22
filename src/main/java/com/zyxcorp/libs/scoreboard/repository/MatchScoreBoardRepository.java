package com.zyxcorp.libs.scoreboard.repository;

import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.nonNull;

public class MatchScoreBoardRepository {

    private Map<Integer, MatchEntity> entities = new ConcurrentHashMap<>();

    public boolean isExists(String homeTeam, String awayTeam) {
        return entities.values().stream()
                .anyMatch(s -> s.getHomeTeamName().equals(homeTeam) && s.getAwayTeamName().equals(awayTeam));
    }

    public boolean isExists(int id) {
        return nonNull(entities.get(id));
    }

    public int insert(MatchEntity entity) {
        int id = idSequence();

        entity.setId(id);
        entity.setCreatedOn(LocalDateTime.now());

        entities.put(id, entity);

        return id;
    }

    public void deleteById(int id) {
        entities.remove(id);
    }

    public MatchEntity findById(int id) {
        return entities.get(id);
    }

    public MatchEntity update(int id, int homeTeamScore, int awayTeamScore) {
        entities.get(id).setHomeTeamPoints(homeTeamScore);
        entities.get(id).setAwayTeamPoints(awayTeamScore);
        entities.get(id).setUpdatedOn(LocalDateTime.now());
        return entities.get(id);
    }

    private int idSequence() {
        return entities.size() + 1;
    }

}
