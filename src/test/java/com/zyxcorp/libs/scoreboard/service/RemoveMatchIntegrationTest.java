package com.zyxcorp.libs.scoreboard.service;

import com.zyxcorp.libs.scoreboard.dto.MatchDto;
import com.zyxcorp.libs.scoreboard.dto.ScoreDto;
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
class RemoveMatchIntegrationTest {

    @InjectMocks
    private MatchScoreBoardService matchScoreBoardService;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
        matchScoreBoardService.create(matchDefaultDto());
    }

    @Test
    void test_remove_validMatch_shouldRemoveMatch_ThenVerifyMatchExists() {

        matchScoreBoardService.delete(1);

        MatchNotFoundException exception = assertThrows(MatchNotFoundException.class,
                () -> matchScoreBoardService.get(1));

        assertEquals("Match not found", exception.getMessage());
        assertEquals("E103", exception.getContract());

    }

    @Test
    void test_remove_invalidMatch_shouldNotFail() {

        MatchNotFoundException exception = assertThrows(MatchNotFoundException.class,
                () -> matchScoreBoardService.get(0));

        assertEquals("Match not found", exception.getMessage());
        assertEquals("E103", exception.getContract());

        matchScoreBoardService.delete(0);
    }


    private MatchDto matchDefaultDto() {
        return new MatchDto(new ScoreDto("Germany", 2), new ScoreDto("France", 2));
    }

}