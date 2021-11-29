package node23.Model;

import java.util.ArrayList;

public class Game {
    private int totalKills;
    private ArrayList<String> players;
    private ArrayList<Kills> killsList;

    public Game() {
    }

    public int getTotalKills() {
        return this.totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public ArrayList<String> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public ArrayList<Kills> getKillsList() {
        return this.killsList;
    }

    public void setKillsList(ArrayList<Kills> killsList) {
        this.killsList = killsList;
    }
}
