package com.zyxcorp.libs.scoreboard;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.dto.ScoreDto;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import com.zyxcorp.libs.scoreboard.service.MatchScoreBoardService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StartMatchIntegrationTest {

    @InjectMocks
    private MatchScoreBoardService matchScoreBoardService;

    @BeforeAll
    public void setup() throws MatchExistsException, InvalidMatchException {
        MockitoAnnotations.openMocks(this);
        matchScoreBoardService.create(matchDefaultDto());
    }

    @Test
    void test_create_validMatch_shouldReturnValidMatchId() throws MatchExistsException, InvalidMatchException {

        int matchId = matchScoreBoardService.create(matchDto());

        assertTrue(matchId > 0);
    }

    @Test
    void test_create_duplicateMatch_shouldThrowMatchExistsException() {

        MatchExistsException exception = assertThrows(MatchExistsException.class,
                () -> matchScoreBoardService.create(matchDefaultDto()));

        assertEquals("Match exists", exception.getMessage());
        assertEquals("E100", exception.getContract());
    }

    @Test
    void test_create_inValidMatch_shouldThrowNullPointerException() {

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> matchScoreBoardService.create(invalidMatchDto()));

        assertEquals("homeTeam is marked non-null but is null", exception.getMessage());
    }

    private MatchDto matchDto() {
        return new MatchDto(new ScoreDto("Mexico", 0), new ScoreDto("Canada", 0));
    }

    private MatchDto matchDefaultDto() {
        return new MatchDto(new ScoreDto("Germany", 2), new ScoreDto("France", 2));
    }

    private MatchDto invalidMatchDto() {
        return new MatchDto(null, new ScoreDto("", -1));
    }

}