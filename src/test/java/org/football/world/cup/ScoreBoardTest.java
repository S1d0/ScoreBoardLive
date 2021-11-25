package org.football.world.cup;

import org.football.world.cup.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    private ScoreBoard board;
    private static final LocalDateTime gameDate = LocalDateTime
            .of(2021,11, 1, 10, 0, 0);

    @Before
    public void init() {
        board = new FootballScoreBoard();
    }

    @Test
    public void shouldBePossibleToStarGame() {
        // When
        board.startGame("Mexico", "Canada", gameDate);

        // Then
        List<Game> activeGames = board.getActiveGames();
        Game activeGame = activeGames.get(0);

        assertEquals(1, activeGames.size());
        assertEquals(0, activeGame.getAwayScore());
        assertEquals(0, activeGame.getHomeScore());
    }

    @Test
    public void shouldBePossibleToFinishGame() {
        // Given
        Game startedGame = board.startGame("Mexico", "Canada", gameDate);

        // When
        board.finishGame(startedGame);

        // Then
        assertFalse(board.getActiveGames().contains(startedGame));
    }

    @Test
    public void shouldBePossibleToUpdateScoreOfGivenGame() {
        // Given
        Game startedGame = board.startGame("Mexico", "Canada", gameDate);

        // When
        board.updateGameScore(startedGame, Score.of(0, 5));
        Game gameWithUpdatedScore = board.getGame(startedGame);

        // Then
        Team home = gameWithUpdatedScore.getHomeTeam();
        Team away = gameWithUpdatedScore.getAwayTeam();

        assertEquals("Mexico", home.getTeamName());
        assertEquals(0, gameWithUpdatedScore.getHomeScore());

        assertEquals("Canada", away.getTeamName());
        assertEquals(5, gameWithUpdatedScore.getAwayScore());
    }

    @Test
    public void summaryOfGamesOrderByTotalScoreAndMostRecent() {
        // Given
        addTestData();

        // When
        List<Game> games = board.getGamesSummary();

        // Then
        Game expectedFirstGame = board.getGameByTeams("Uruguay", "Italy");
        Game expectedSecondGame = board.getGameByTeams("Spain", "Brazil");
        Game expectedLastGame = board.getGameByTeams("Germany", "France");

        assertEquals(games.get(0), expectedFirstGame);
        assertEquals(games.get(1), expectedSecondGame);
        assertEquals(games.get(games.size()-1), expectedLastGame);
    }

    private void addTestData() {
        Game uruguayItalyGame = board.startGame("Uruguay", "Italy", gameDate);
        Game spainBrazilGame = board.startGame("Spain", "Brazil", gameDate.minusHours(1));
        Game mexicoCanadaGame = board.startGame("Mexico", "Canada", gameDate.minusDays(1));
        Game argentinaAustralia = board.startGame("Argentina", "Australia", gameDate.minusDays(1));
        Game germanyFranceGame = board.startGame("Germany", "France", gameDate.minusDays(2));

        board.updateGameScore(mexicoCanadaGame, Score.of(0, 5));
        board.updateGameScore(spainBrazilGame, Score.of(10, 2));
        board.updateGameScore(germanyFranceGame, Score.of(2,2));
        board.updateGameScore(uruguayItalyGame, Score.of(6, 6));
        board.updateGameScore(argentinaAustralia, Score.of(3,1));
    }
}
