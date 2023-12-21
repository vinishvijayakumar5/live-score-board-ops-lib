package com.zyxcorp.libs.scoreboard.repository;

import com.zyxcorp.libs.scoreboard.entitiy.MatchEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MatchScoreBoardRepository {

    private Map<Integer, MatchEntity> entities = new ConcurrentHashMap<>();

    public int insert(MatchEntity entity) {
        return 0;
    }

}
