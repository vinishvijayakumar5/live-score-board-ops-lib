package com.zyxcorp.libs.scoreboard.repository;

import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatchScoreBoardRepository {

    private Map<Integer, MatchEntity> entities = new ConcurrentHashMap<>();

    public boolean isExists(String homeTeam, String awayTeam) {
        return entities.values().stream()
                .anyMatch(s -> s.getHomeTeamName().equals(homeTeam) && s.getAwayTeamName().equals(awayTeam));
    }

    public int insert(MatchEntity entity) {
        int id = idSequence();

        entity.setId(id);
        entity.setCreatedOn(LocalDateTime.now());

        entities.put(id, entity);

        return id;
    }

    public MatchEntity findById(int id) {
        return null;
    }

    private int idSequence() {
        return entities.size() + 1;
    }

}
