package node23.Model;

import java.util.ArrayList;

public class Game {
    private int id;
    private int totalKills;
    private ArrayList<Player> players;
    private int startLine;
    private int endLine;

    public Game(int id, int startLine, int endLine) {
        this.id = id;
        this.startLine = startLine;
        this.endLine = endLine;
        this.players = new ArrayList<Player>();
        this.totalKills = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalKills() {
        return this.totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public void addTotalKills() {
        this.totalKills++;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getStartLine() {
        return this.startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return this.endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    @Override
    public String toString() {
        String gameResume = "";
        gameResume += "game_" + getId() + ": {\n";
        gameResume += "  total_kills: " + getTotalKills() + ";\n";
        gameResume += "  " + playersList() + "\n";
        gameResume += "  kills:{\n" + killsList() + "\n  }\n";  
        gameResume +="}\n";
        return gameResume;
    }

    private String killsList(){
        String resume = "";
        for (int i = 0; i < getPlayers().size(); i++) {
            if(i == getPlayers().size()-1){
                resume += "    '" + players.get(i).getName() + "': " + players.get(i).getKills();
                continue;
            }
            resume += "    '" + players.get(i).getName() + "': " + players.get(i).getKills() + ",\n";
        }
        return resume;
    }

    private String playersList() {
        String resume = "";
        String playersList = "";
        for (int i = 0; i < getPlayers().size(); i++) {
            if(i == getPlayers().size()-1){
                playersList += "'" + players.get(i).getName() + "'";
                continue;
            }            
            playersList += "'" + players.get(i).getName() + "', ";
        }
        resume += "players: [" + playersList + "]";
        return resume;
    }
}
