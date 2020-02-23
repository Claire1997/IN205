import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);       
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai
    // instead.
    
    public void putship(List<AbstractShip> ships) {
        AbstractShip[] ship = ships.toArray(new AbstractShip[0]);
        ai.putShips(ship);
    }
    
    public Boolean success(List<AbstractShip> myShips,List<AbstractShip> otherShips) {
    	AbstractShip[] ship1 = myShips.toArray(new AbstractShip[0]);
    	AbstractShip[] ship2 = otherShips.toArray(new AbstractShip[0]);
    	int restShips;
    	
    	ai.CountDestory=0;
    	restShips=myShips.size();
        for(int k = 0;k<otherShips.size();k++) {
        	if(ship2[k].isSunk())
        		ai.CountDestory++;
        }
        for(int k = 0;k<myShips.size();k++) {
        	if(ship1[k].isSunk())
        		restShips--;
        }
    	if((ai.CountDestory==otherShips.size())&&(restShips>0)) {
    		return true;
    	}
    	else if((ai.CountDestory<otherShips.size())&&(restShips==0)) {
    		return false;
    	}
    	else {
    		return null;
    	}
    }
    
    public void sendHit() {
    	int[] coords = {0,0};
    	
    	coords=ai.pickRandomCoord();        
        ai.sendHit(coords);
        //return coords;
    }
}
