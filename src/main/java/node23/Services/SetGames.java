package node23.Services;

import java.util.ArrayList;

import node23.Model.Game;
import node23.Model.Player;

public class SetGames {

    public static void instantiateGames() {
        ArrayList<String> logLines = LogReader.ReadLog();
        ArrayList<Game> games = LogParser.getGames();
        for (Game game : games) {
            setGame(game, logLines);
        }
        for (Game game : games) {
            System.out.println(game.toString());
        }

        // TODO run function setGame to all games in games (This must be in parallel)
    }

    public static void setGame(Game game, ArrayList<String> logLines) {

        for (int i = game.getStartLine(); i < game.getEndLine(); i++) {
            String line = logLines.get(i);
            if (line.contains("ClientUserinfoChanged:")) {
                registerPlayer(line, game);
            }
            if (line.contains("Kill:")) {
                registerKill(line, game);
            }
        }
    }

    public static void registerKill(String line, Game game) {
        game.addTotalKills();
        int lineIndex = findCorrectKillLineIndex(line);
        int killerId = Integer.parseInt(line.split(" ")[lineIndex + 1].split(":")[0]);
        int deadId = Integer.parseInt(line.split(" ")[lineIndex + 2]);

        if (killerId == 1022) {
            Player dead = playerAlreadyExists(deadId, game.getPlayers());
            if (dead.getKills() > 0) {
                dead.subKills();
            }
            return;
        }
        playerAlreadyExists(killerId, game.getPlayers()).addKill();
    }

    public static int findCorrectKillLineIndex(String line) {
        String[] lineBroke = line.split(" ");
        for (int i = 0; i < lineBroke.length; i++) {
            if (lineBroke[i].contains("Kill:")) {
                return i;
            }
        }
        return -1;
    }

    public static void registerPlayer(String line, Game game) {
        int lineIndex = findCorrectPlyaerLineIndex(line);
        int playerId = Integer.parseInt(line.split(" ")[lineIndex + 1]);
        String playerInfo = line.split(" ")[lineIndex + 2];
        String playerName = playerInfo.split("\\\\")[1];
        Player existentPlayer = playerAlreadyExists(playerId, game.getPlayers());
        if (existentPlayer == null) {
            Player player = new Player(playerId, playerName);
            game.addPlayer(player);
        } else {
            existentPlayer.setName(playerName);
        }
    }

    public static int findCorrectPlyaerLineIndex(String line) {
        String[] lineBroke = line.split(" ");
        for (int i = 0; i < lineBroke.length; i++) {
            if (lineBroke[i].contains("ClientUserinfoChanged:")) {
                return i;
            }
        }
        return -1;
    }

    public static Player playerAlreadyExists(int id, ArrayList<Player> players) {
        if (players.size() == 0) {
            return null;
        }
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }
}
