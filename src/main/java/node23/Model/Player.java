package node23.Model;

public class Player {
    private int id;
    private String name;
    private int kills;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.kills = 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKills() {
        return this.kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill(){
        this.kills++;
    }

    public void subKills(){
        this.kills--;
    }

    @Override
    public String toString() {
        return getName() + " : " + getKills();
    }
}
