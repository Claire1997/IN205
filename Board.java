public class Board implements IBoard{
    protected String name;
    char[][] navire;
    boolean[][] frappe;

    public Board(String n, int h, int w) {
        this.name = n;
        this.navire = new char[h][w];
        this.frappe = new boolean[h][w];
        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
                navire[i][j] = '.';
                frappe[i][j] = false;
            }
        }
    }

    public Board(String n) {
        this.name = n;
        this.navire = new char[10][10];
        this.frappe = new boolean[10][10];
        for (int i=0; i<10; i++) {
            for (int j=0; j<10; j++) {
                navire[i][j] = '.';
                frappe[i][j] = false;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public char getNavire(int i, int j) {
        return navire[i][j];
    }

    public void setNavire(char c, int i, int j) {
        this.navire[i][j] = c;
    }

    public boolean getFrappe(int i, int j) {
        return frappe[i][j];
    }

    public void setFrappe(boolean b, int i, int j) {
        this.frappe[i][j] = b;
    }

    public void print() {
        System.out.println("Board" + name);
        System.out.println("Navires :");
        System.out.print("   ");
        char c;
        for (int i=0; i<navire[0].length-1; i++) {
            c = (char)('A' + i);
            System.out.print(c + " ");
        }
        c = (char)('A' + navire[0].length - 1);
        System.out.println(c);

        for (int i=0; i<navire.length; i++) {
            System.out.print(i+1);
            System.out.print(" ");
            if (i<9) System.out.print(" ");
            for (int j=0; j<navire[0].length-1; j++) {
                System.out.print(navire[i][j] + " ");
            }
            System.out.println(navire[i][navire[0].length-1]);
        }

        System.out.println("Frappes :");
        System.out.print("   ");
        for (int i=0; i<frappe[0].length-1; i++) {
            c = (char)('A' + i);
            System.out.print(c + " ");
        }
        c = (char)('A' + navire[0].length - 1);
        System.out.println(c);

        for (int i=0; i<navire.length; i++) {
            System.out.print(i+1);
            System.out.print(" ");
            if (i<9) System.out.print(" ");
            for (int j=0; j<navire[0].length-1; j++) {
                if (frappe[i][j]==false) System.out.print(". ");
                else System.out.print("x ");
            }
            if (frappe[i][navire[0].length-1]==false)  System.out.println(".");
            else System.out.println("x");
        }
    }

    public int getSize() {
        return navire.length * navire[0].length;
    }

    public boolean  putShip(AbstractShip ship, int x, int y) {
        if (x<0 || x>=navire.length || y<0 || y>navire[0].length) {
            System.out.println("Error, ship is not on the battlefield.");
            return false;
        }
        int x_local=x, y_local=y;
        switch (ship.orientation) {
            case NORTH:
                if (x+ship.taille>=navire.length) {
                    System.out.println("Error, ship is not on the battlefield.");
                    return false;
                }
                for (int i=0; i<ship.taille; i++) {
                    x_local = x + i;
                    if (navire[x_local][y_local]!='.') {
                        System.out.println("Error, already one ship in this position.");
                        return false;
                    }
                }
                x_local = x;
                for (int i=0; i<ship.taille; i++) {
                    setNavire(ship.label, x_local, y_local);
                    x_local += 1;
                }
                break;
            
            case SOUTH:
                if (x-ship.taille<0) {
                    System.out.println("Error, ship is not on the battlefield.");
                    return false;
                }
                for (int i=0; i<ship.taille; i++) {
                    x_local = x - i;
                    if (navire[x_local][y_local]!='.') {
                        System.out.println("Error, already one ship in this position.");
                        return false;
                    }
                }
                x_local = x;
                for (int i=0; i<ship.taille; i++) {
                    setNavire(ship.label, x_local, y_local);
                    x_local -= 1;
                }
                break;

            case WEST:
                if (y+ship.taille>=navire[0].length) {
                    System.out.println("Error, ship is not on the battlefield.");
                    return false;
                }
                for (int i=0; i<ship.taille; i++) {
                    y_local = y + i;
                    if (navire[x_local][y_local]!='.') {
                        System.out.println("Error, already one ship in this position.");
                        return false;
                    }
                }
                y_local = y;
                for (int i=0; i<ship.taille; i++) {
                    setNavire(ship.label, x_local, y_local);
                    y_local += 1;
                }
                break;

            default: // EAST
                if (y-ship.taille<0) {
                    System.out.println("Error, ship is not on the battlefield.");
                    return false;
                }
                for (int i=0; i<ship.taille; i++) {
                    y_local = y - i;
                    if (navire[x_local][y_local]!='.') {
                        System.out.println("Error, already one ship in this position.");
                        return false;
                    }
                }
                y_local = y;
                for (int i=0; i<ship.taille; i++) {
                    setNavire(ship.label, x_local, y_local);
                    y_local -= 1;
                }
                break;
        }
        return true;
    }
    public boolean hasShip(int x, int y) {
        if (navire[x][y]!='.') return true;
        else return false;
    }

    // ????
    public void setHit(boolean hit, int x, int y) { 
       frappe[x][y] = hit;
    }

    public Boolean getHit(int x, int y) {
        return frappe[x][y];
    }
}