package node23.Model;

public class DeathTypes {
    private MeansOfDeath MeanOfDeath;
    private int quantity;

    public DeathTypes(MeansOfDeath MeanOfDeath) {
        this.MeanOfDeath = MeanOfDeath;
        this.quantity = 0;
    }

    public MeansOfDeath getMeanOfDeath() {
        return this.MeanOfDeath;
    }

    public void setMeanOfDeath(MeansOfDeath MeanOfDeath) {
        this.MeanOfDeath = MeanOfDeath;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void addQuantity(){
        this.quantity++;
    }
}
