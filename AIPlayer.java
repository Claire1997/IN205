import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;
    public AbstractShip[] ship;

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
        ship = ships.toArray(new AbstractShip[0]);
        ai.putShips(ship);
    }
    
    public Boolean success(List<AbstractShip> myShips, List<AbstractShip> otherShips) { // ????变量没有根据环境变化了！！
    	AbstractShip[] ship1 = myShips.toArray(new AbstractShip[0]);
    	AbstractShip[] ship2 = otherShips.toArray(new AbstractShip[0]);
    	int restShips = myShips.size();
    	
        ai.CountDestory=0;
        System.out.println("rest " + restShips + "destroy" + ai.CountDestory);
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
        
        /*boolean rest = false;
        boolean destroy = true;

        for (int i=0; i<ai.board.getSize(); i++) {
            for (int j=0; j<ai.board.getSize(); j++) {
                if (ai.getBoard().getNavire(i,j)!=null && ai.getBoard().getFrappe(i,j)==null) {
                    rest = true;
                    break;
                }
            }
            if (rest==true) break;
        }
        for (int i=0; i<ai.opponent.getSize(); i++) {
            for (int j=0; j<ai.opponent.getSize(); j++) {
                if (ai.getOpponent().getNavire(i,j)!=null && ai.getOpponent().getFrappe(i,j)==null) {
                    destroy = false;
                    break;
                }
            }
            if (destroy==false) break;
        }
        if (rest&&destroy) return true;
        else if ((!rest)&&(!destroy)) return false;
        else return null;
        */
    }
    
    public void sendHit() {
    	int[] coords = {0,0};
    	
        coords=ai.pickRandomCoord();  
        System.out.println(coords[0]);    
        System.out.println(coords[1]);     
        ai.sendHit(coords);
        //return coords;
    }
}
