package com.zyxcorp.libs.scoreboard.repository;

import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatchScoreBoardRepository {

    private Map<Integer, MatchEntity> matches = new ConcurrentHashMap<>();

    public boolean isExists(String homeTeam, String awayTeam) {
        return matches.values().stream()
                .anyMatch(s -> s.getHomeTeamName().equalsIgnoreCase(homeTeam) && s.getAwayTeamName().equalsIgnoreCase(awayTeam));
    }

    public int insert(MatchEntity entity) {
        int id = idSequence();

        entity.setId(id);
        entity.setCreatedOn(LocalDateTime.now());

        matches.put(id, entity);

        return id;
    }

    public void deleteById(int id) {
        matches.remove(id);
    }

    public MatchEntity findById(int id) {
        return matches.get(id);
    }

    public MatchEntity update(int id, int homeTeamScore, int awayTeamScore) {
        matches.get(id).setHomeTeamPoints(homeTeamScore);
        matches.get(id).setAwayTeamPoints(awayTeamScore);
        matches.get(id).setUpdatedOn(LocalDateTime.now());
        return matches.get(id);
    }

    public List<MatchEntity> findAll() {
        return matches.values().stream().toList();
    }

    private int idSequence() {
        return matches.size() + 1;
    }

}
