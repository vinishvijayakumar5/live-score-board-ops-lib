package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.dto.ScoreDto;
import com.zyxcorp.libs.scoreboard.exception.InvalidScoreException;
import com.zyxcorp.libs.scoreboard.exception.MatchNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdateMatchIntegrationTest {

    @InjectMocks
    private MatchScoreBoardService matchScoreBoardService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        matchScoreBoardService.create(matchDefaultDto());
    }

    @Test
    void test_update_validMatch_shouldUpdateMatch_ThenReturnUpdatedMatch() {

        MatchDto dto = matchScoreBoardService.update(1, 4, 3);

        assertEquals("Germany", dto.homeTeam().name());
        assertEquals(4, dto.homeTeam().points());

        assertEquals("France", dto.awayTeam().name());
        assertEquals(3, dto.awayTeam().points());

    }

    @Test
    void test_update_invalidMatch_shouldThrowMatchNotFoundException() {

        MatchNotFoundException exception = assertThrows(MatchNotFoundException.class,
                () -> matchScoreBoardService.update(0, 4, 3));

        assertEquals("Match not found", exception.getMessage());
        assertEquals("E103", exception.getContract());

    }

    @Test
    void test_update_invalidMatchScore_shouldThrowInvalidScoreException() {

        InvalidScoreException exception = assertThrows(InvalidScoreException.class,
                () -> matchScoreBoardService.update(1, 1, 2));

        assertEquals("Invalid match score", exception.getMessage());
        assertEquals("E104", exception.getContract());

    }

    private MatchDto matchDefaultDto() {
        return new MatchDto(new ScoreDto("Germany", 2), new ScoreDto("France", 2));
    }


}