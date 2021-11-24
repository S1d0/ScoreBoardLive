package org.football.world.cup.domain;

import java.util.List;

public interface ScoreBoard {

    Game startGame(String homeTeam, String awayTeam);

    List<Game> getActiveGames();

    Game finishGame(Game activeGame);

    Game getGame(Game activeGame);

    void updateGameScore(Game activeGame, Score score);

    List<Game> getGamesSummary();

    Game getGameByTeams(String homeTeam, String awayTeam);

}
