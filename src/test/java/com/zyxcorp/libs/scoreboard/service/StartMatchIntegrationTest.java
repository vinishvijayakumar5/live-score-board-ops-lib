package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.dto.ScoreDto;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StartMatchIntegrationTest {

    @InjectMocks
    private MatchScoreBoardService matchScoreBoardService;

    @Test
    void test_create_validMatch_shouldReturnValidMatchId() {

        int matchId = matchScoreBoardService.create(matchDto());

        assertTrue(matchId > 0);
    }

    @Test
    void test_create_duplicateMatch_shouldThrowMatchExistsException() {

        MatchExistsException exception = assertThrows(MatchExistsException.class,
                () -> matchScoreBoardService.create(matchDto()));

        assertEquals("Match exists", exception.getMessage());
        assertEquals("E100", exception.getContract());
    }

    @Test
    void test_create_inValidMatch_shouldThrowInvalidMatchException() {

        InvalidMatchException exception = assertThrows(InvalidMatchException.class,
                () -> matchScoreBoardService.create(matchDto()));

        assertEquals("Invalid match", exception.getMessage());
        assertEquals("E101", exception.getContract());
    }

    private MatchDto matchDto() {
        return new MatchDto(new ScoreDto("Mexico", 0), new ScoreDto("Canada", 0));
    }
}