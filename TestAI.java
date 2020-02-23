import java.util.ArrayList;
import java.util.List;

public class TestAI {
	public static void main(String[] args) {
		Board B1 = new Board("B1", 10, 10);
        Board B2 = new Board("B2", 10, 10);
        Destroyer d = new Destroyer();
        Submarine s1 = new Submarine();
        Submarine s2 = new Submarine();
        BattleShip b = new BattleShip();
        Aircraft_Carrier a = new Aircraft_Carrier();
        
        List<AbstractShip> ships1 = new ArrayList<AbstractShip>();
        ships1.add(d);
        ships1.add(s1);
        ships1.add(s2);
        ships1.add(b);
        ships1.add(a);
        
        List<AbstractShip> ships2 = new ArrayList<AbstractShip>();
        ships2.add(d);
        ships2.add(s1);
        ships2.add(s2);
        ships2.add(b);
        ships2.add(a);
        
        AIPlayer AI1 = new AIPlayer(B1,B2,ships1);
        AIPlayer AI2 = new AIPlayer(B2,B1,ships2);
        System.out.println("AI1 put ship:");
        AI1.putship(ships1);
        System.out.println();
        System.out.println("AI2 put ship:");
        AI2.putship(ships2);
        System.out.println();
        
        //B1.print();
        //B2.print();
        
        int count=0;
        
        while((AI1.success(ships1, ships2)==null)||(count>=100)) {
        	AI1.sendHit();
        	AI2.sendHit();
        	count++;
        }
        B1.print();
        B2.print();
        
        if(AI1.success(ships1, ships2)) {
        	System.out.println("AI1 success");
        }
        else {
        	System.out.println("AI2 success");
        }
        
	}

}
