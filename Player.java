import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getTaille());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientation
            if(res!=null){
                Orientation o = Orientation.EAST;
                switch (res.orientation) {
                    case "n":
                        o = Orientation.NORTH;
                        break;
                    case "s":
                        o = Orientation.SOUTH;
                        break;
                    case "w":
                        o = Orientation.WEST;
                        break;
                    case "e":
                        o = Orientation.EAST;
                        break;
                }
                s.setOrientation(o);
            }
            else
            	i--;
            // TODO put ship at given position
            if(res!=null) {
            	if(!board.putShip(s,res.x,res.y)) {
                    System.out.println("Surpass the limit of position! Change the position or the orientation.");
                    s.print();
            		i--;
            	}
            }
            // TODO when ship placement successful
            ++i;
            done = i == 5;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = true;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
