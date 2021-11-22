package org.football.world.cup;

import org.football.world.cup.domain.FootballTeam;
import org.football.world.cup.domain.Game;
import org.football.world.cup.domain.ScoreBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ScoreBoardTest {

    private ScoreBoard board;

    @Before
    public void init() {
        board = new ScoreBoard();
    }

    @Test
    public void shouldBePossibleToStarGame() {
        // When
        board.startGame("Mexico", "Canada");

        // Then
        List<Game> activeGames = board.getActiveGames();
        assertEquals(1, activeGames.size());
    }

    @Test
    public void shouldBePossibleToFinishGame() {
        // Given
        Game startedGame = board.startGame("Mexico", "Canada");

        // When
        Game finishedGame = board.finishGame(startedGame);

        // Then
        assertFalse(finishedGame.isActive());
        assertEquals(finishedGame, startedGame);
        assertEquals(0, board.getActiveGames().size());
    }

    @Test
    public void shouldBePossibleToUpdateScoreOfGivenGame() {
        // Given
        Game startedGame = board.startGame("Mexico", "Canada");

        // When
        board.updateGameScore(startedGame, new Score(0, 5));
        Game gameWithUpdatedScore = board.getGame(startedGame);

        // Then
        FootballTeam home = gameWithUpdatedScore.getHomeTeam();
        FootballTeam away = gameWithUpdatedScore.getAwayTeam();

        assertEquals(home.getTeamName(), "Mexico");
        assertEquals(home.getScore(), 0);

        assertEquals(away.getTeamName(), "Canada");
        assertEquals(away.getScore(), 5);
    }

    @Test
    public void summaryOfGamesOrderByTotalScoreAndMostRecent() {
        // Given
        addTestData();

        // When
        List<Game> games = board.getGamesSummary();

        // Then
        Game expectedFirstGame = board.getGameByTeams("Urugua", "Italy");
        Game expectedLastGame = board.getGameByTeams("Germany", "France");

        assertEquals(games.get(0), expectedFirstGame);
        assertEquals(games.get(games.size()-1), expectedLastGame);
    }

    private void addTestData() {
        Game mexicoCanadaGame = board.startGame("Mexico", "Canada");
        Game spainBrazilGame = board.startGame("Spain", "Brazil");
        Game germanyFranceGame = board.startGame("Germany", "France");
        Game uruguayItalyGame = board.startGame("Uruguay", "Italy");
        Game argentinaAustralia = board.startGame("Argentina", "Australia");

        board.updateScore(mexicoCanadaGame, new Score(0, 5));
        board.updateScore(spainBrazilGame, new Score(10, 2));
        board.updateScore(germanyFranceGame, new Score(2, 2));
        board.updateScore(uruguayItalyGame, new Score(6, 6));
        board.updateScore(argentinaAustralia, new Score(3, 1));
    }
}