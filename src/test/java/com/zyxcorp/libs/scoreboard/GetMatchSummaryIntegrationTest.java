package com.zyxcorp.libs.scoreboard;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.dto.ScoreDto;
import com.zyxcorp.libs.scoreboard.exception.InvalidMatchException;
import com.zyxcorp.libs.scoreboard.exception.MatchExistsException;
import com.zyxcorp.libs.scoreboard.exception.MatchNotFoundException;
import com.zyxcorp.libs.scoreboard.service.MatchScoreBoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GetMatchSummaryIntegrationTest {

    @InjectMocks
    private MatchScoreBoardService matchScoreBoardService;

    @Test
    void test_get_shouldReturnAllMatchOrderByTotalScoreAndCreatedTime() throws MatchNotFoundException {

        setupMatches();

        List<MatchDto> matches = matchScoreBoardService.get();

        assertNotNull(matches);
        assertEquals(5, matches.size());

        assertMatch(matches.get(0), "Uruguay", 6, "Italy", 6);
        assertMatch(matches.get(1), "Spain", 10, "Brazil", 2);
        assertMatch(matches.get(2), "Mexico", 0, "Canada", 5);
        assertMatch(matches.get(3), "Argentina", 3, "Australia", 1);
        assertMatch(matches.get(4), "Germany", 2, "France", 2);

    }

    @Test
    void test_get_whenNoMatchFound_shouldThrowMatchNotFoundException() {

        MatchNotFoundException exception = assertThrows(MatchNotFoundException.class,
                () -> matchScoreBoardService.get());

        assertEquals("No matches not found", exception.getMessage());
        assertEquals("E106", exception.getContract());

    }

    private void setupMatches() {
        allMatches().stream().forEach(m -> {
                    try {
                        matchScoreBoardService.create(m);
                    } catch (MatchExistsException | InvalidMatchException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private List<MatchDto> allMatches() {
        return List.of(new MatchDto(new ScoreDto("Mexico", 0), new ScoreDto("Canada", 5)),
                new MatchDto(new ScoreDto("Spain", 10), new ScoreDto("Brazil", 2)),
                new MatchDto(new ScoreDto("Germany", 2), new ScoreDto("France", 2)),
                new MatchDto(new ScoreDto("Uruguay", 6), new ScoreDto("Italy", 6)),
                new MatchDto(new ScoreDto("Argentina", 3), new ScoreDto("Australia", 1)));
    }

    private void assertMatch(MatchDto match,
                             String homeTeamName, int homeTeamScore,
                             String awayTeamName, int awayTeamScore) {
        assertEquals(homeTeamName, match.homeTeam().name());
        assertEquals(homeTeamScore, match.homeTeam().points());
        assertEquals(awayTeamName, match.awayTeam().name());
        assertEquals(awayTeamScore, match.awayTeam().points());
    }


}