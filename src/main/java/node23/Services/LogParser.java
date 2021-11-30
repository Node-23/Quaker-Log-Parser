package node23.Services;

import java.util.ArrayList;

import node23.Model.Game;

public class LogParser {
    
    public static ArrayList<Game> getGames(){
        ArrayList<Game> games = new ArrayList<Game>();
        ArrayList<String> logLines = LogReader.ReadLog();
        boolean newGame = false;
        int id = 1;
        int start = 0;
        for (int i = 0; i < logLines.size(); i++) {
            if(newGame == false && logLines.get(i).contains("InitGame:")){
                start = i+1;
                newGame = true;
            }
            if(newGame == true && logLines.get(i).contains(" ------------------------------------------------------------")){
                Game game = new Game(id, start, i);
                games.add(game);
                newGame = false;
                id++;
            }
        }
        return games;
    }
}
