package node23.Services;

import java.util.ArrayList;

import node23.Model.DeathTypes;
import node23.Model.Game;
import node23.Model.MeansOfDeath;
import node23.Model.Player;

public class SetGames {

    static ArrayList<String> logLines = LogReader.ReadLog();
    static ArrayList<Game> games = LogParser.getGames();
    static ArrayList<Integer> doneList = new ArrayList<Integer>();
    static int index = 0;
    static int iterationIndex;

    public static ArrayList<Game> instantiateGames() {
        while(doneList.size() <21){
            if(index >= 21){
                index = 0;
            }
            new Thread(t1).start();
            index++;
        }
        return games;
    }
    
    private static Runnable t1 = new Runnable() {
        public void run() {
            setGame(logLines);
        }
    };

    public static boolean isDone(int index){
        ArrayList<Integer> localDoneList = doneList;
        for (Integer done : localDoneList) {
            if(index == done) {
                return true;
            }
        }
        return false;
    }

    public static void setGame(ArrayList<String> logLines) {
        int localIndex = index;
        if(isDone(localIndex) == true){
            return;
        }
        doneList.add(localIndex);
        Game game = games.get(localIndex);

        for (int i = game.getStartLine(); i < game.getEndLine(); i++) {
            String line = logLines.get(i);
            if (line.contains("ClientUserinfoChanged:")) {
                registerPlayer(line, game);
            }
            if (line.contains("Kill:")) {
                registerKill(line, game);
                registerTypeOfDeath(line, game);
            }
        }
    }

    public static void registerTypeOfDeath(String line, Game game){
        int lineIndex = findCorrectKillLineIndex(line);
        String typeOfDeathString = line.split(" ")[lineIndex + 8];
        MeansOfDeath typeOfDeath = getMeanOfDeath(typeOfDeathString);
        DeathTypes deathType = typeAlreadyExists(typeOfDeath, game);
        if(deathType == null){
            deathType = new DeathTypes(typeOfDeath);
            deathType.addQuantity();
            game.addGameDeath(deathType);
        }else{
            deathType.addQuantity();
        }
    }

    public static DeathTypes typeAlreadyExists(MeansOfDeath typeOfDeath, Game game){
        for (DeathTypes gameDeath : game.getGameDeaths()) {
            if(gameDeath.getMeanOfDeath() == typeOfDeath){
                return gameDeath;
            }
        }
        return null;
    } 

    public static MeansOfDeath getMeanOfDeath(String type) {
        switch (type) {
            case "MOD_UNKNOWN":
                return MeansOfDeath.MOD_UNKNOWN;
            case "MOD_SHOTGUN":
                return MeansOfDeath.MOD_SHOTGUN;
            case "MOD_GAUNTLET":
                return MeansOfDeath.MOD_GAUNTLET;
            case "MOD_MACHINEGUN":
                return MeansOfDeath.MOD_MACHINEGUN;    
            case "MOD_GRENADE":
                return MeansOfDeath.MOD_GRENADE;
            case "MOD_GRENADE_SPLASH":
                return MeansOfDeath.MOD_GRENADE_SPLASH;    
            case "MOD_ROCKET":
                return MeansOfDeath.MOD_ROCKET;
            case "MOD_ROCKET_SPLASH":
                return MeansOfDeath.MOD_ROCKET_SPLASH;
            case "MOD_PLASMA":
                return MeansOfDeath.MOD_PLASMA;
            case "MOD_PLASMA_SPLASH":
                return MeansOfDeath.MOD_PLASMA_SPLASH;
            case "MOD_RAILGUN":
                return MeansOfDeath.MOD_RAILGUN;
            case "MOD_LIGHTNING":
                return MeansOfDeath.MOD_LIGHTNING;
            case "MOD_BFG":
                return MeansOfDeath.MOD_BFG;
            case "MOD_BFG_SPLASH":
                return MeansOfDeath.MOD_BFG_SPLASH;
            case "MOD_WATER":
                return MeansOfDeath.MOD_WATER;
            case "MOD_SLIME":
                return MeansOfDeath.MOD_SLIME;
            case "MOD_LAVA":
                return MeansOfDeath.MOD_LAVA;
            case "MOD_CRUSH":
                return MeansOfDeath.MOD_CRUSH;
            case "MOD_TELEFRAG":
                return MeansOfDeath.MOD_TELEFRAG;
            case "MOD_FALLING":
                return MeansOfDeath.MOD_FALLING;
            case "MOD_SUICIDE":
                return MeansOfDeath.MOD_SUICIDE;
            case "MOD_TARGET_LASER":
                return MeansOfDeath.MOD_TARGET_LASER;
            case "MOD_TRIGGER_HURT":
                return MeansOfDeath.MOD_TRIGGER_HURT;
            case "MOD_NAIL":
                return MeansOfDeath.MOD_NAIL;
            case "MOD_CHAINGUN":
                return MeansOfDeath.MOD_CHAINGUN;
            case "MOD_PROXIMITY_MINE":
                return MeansOfDeath.MOD_PROXIMITY_MINE;
            case "MOD_KAMIKAZE":
                return MeansOfDeath.MOD_KAMIKAZE;
            case "MOD_JUICED":
                return MeansOfDeath.MOD_JUICED;
            case "MOD_GRAPPLE":
                return MeansOfDeath.MOD_GRAPPLE;
            default:
                return MeansOfDeath.MOD_UNKNOWN;
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
