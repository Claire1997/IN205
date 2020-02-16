public abstract class AbstractShip {
    protected char label;
    protected String name;
    protected int taille;
    protected Orientation orientation;
    protected int count_strike;

    public AbstractShip(char l, String n, int t, Orientation o) {
        this.label = l; 
        this.name = n;
        this.taille = t;
        this.orientation = o;
        this.count_strike = 0;
    }

    public char getLabel() {
        return label;
    }

    public void setLabel(char l) {
        this.label = l;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        this.name = n;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int t) {
        this.taille = t;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation o) {
        this.orientation = o;
    }

    public int getCountStrike() {
        return count_strike;
    }

    public void addStrike() {
        count_strike ++;
    }
 
    public void print() {
        System.out.println("Ship" + label);
        System.out.println("Name" + label);
        System.out.println("Size" + taille);
        System.out.println("Orientation" + orientation);
    }

    public boolean isSunk() {
        if (count_strike==taille) return true;
        else return false;
    }

}