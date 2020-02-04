public abstract class AbstractShip {
    protected char label;
    protected String name;
    protected int taille;
    protected Orientation orientation;

    public AbstractShip(char l, String n, int t, Orientation o) {
        this.label = l; 
        this.name = n;
        this.taille = t;
        this.orientation = o;
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
 
}