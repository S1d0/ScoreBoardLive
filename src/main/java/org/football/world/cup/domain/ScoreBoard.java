package org.football.world.cup.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface ScoreBoard {

    Game startGame(String homeTeam, String awayTeam, LocalDateTime start);

    List<Game> getActiveGames();

    void finishGame(Game activeGame);

    Game getGame(Game activeGame);

    void updateGameScore(Game activeGame, Score score);

    List<Game> getGamesSummary();

    Game getGameByTeams(String homeTeam, String awayTeam);

}
