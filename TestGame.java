import java.util.List;
import java.util.ArrayList;

public class TestGame {
	public static void main(String[] args) {
		Board B1 = new Board("B1", 10, 10);
        Board B2 = new Board("B2", 10, 10);
        Destroyer d = new Destroyer();
        Submarine s1 = new Submarine();
        Submarine s2 = new Submarine();
        BattleShip b = new BattleShip();
        Aircraft_Carrier a = new Aircraft_Carrier();
        List<AbstractShip> shipList = new ArrayList<AbstractShip>();
        shipList.add(d);
        shipList.add(s1);
        shipList.add(s2);
        shipList.add(b);
        shipList.add(a);
        AbstractShip[] ships = shipList.toArray(new AbstractShip[0]);
        BattleShipsAI ai= new BattleShipsAI(B1,B1);
		int[] coords = {0,0};
        
        ai.putShips(ships);
        for(int i = 0;(i<B1.getSize())&&(ai.CountDestory<shipList.size());i++) {
        	for(int j=0;(j<B1.getSize())&&(ai.CountDestory<shipList.size());j++) {
                Hit h = ai.sendHit(coords);
                System.out.println("Coord hit (" + coords[0] + "," + coords[1] + ")");
                System.out.println(h.toString());
                B1.print();
                coords=ai.randomHit();
                ai.CountDestory=0;
                for(int k = 0;k<shipList.size();k++) {
                	if(ships[k].isSunk())
                		ai.CountDestory++;
                }
        	
        	    if(ai.CountDestory==shipList.size()) {
            	    System.out.println("success");
                    break;
                }
                sleep(300);
            }
        }
        
        B1.print();
        //ai.board.print();
    }
    
    private static void sleep(int ms) { try {
        Thread.sleep(ms);
        } catch (InterruptedException e) {
        e.printStackTrace(); }
        }
}