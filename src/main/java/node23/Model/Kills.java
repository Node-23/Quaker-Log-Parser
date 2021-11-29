package node23.Model;

public class Kills {
    private String player;
    private int totalKills;

    public Kills(String player, int totalKills) {
        this.player = player;
        this.totalKills = totalKills;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
    
    public int getTotalKills() {
        return this.totalKills;
    }
    
    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }
    
    public void addKill(){
        this.totalKills++;
    }
    
    @Override
    public String toString() {
        return getPlayer() + " : " + getTotalKills();
    }

}
