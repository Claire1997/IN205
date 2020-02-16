/** 
 * This class will try to test all the class that we have written
 * **/ 
import java.util.List;
import java.util.ArrayList;

public class TestBoard {
    public static void main(String[] args) {
        // Q2()；
        // Q3();
        // Q4();
        Q6();
    }

    public static void Q2() {
        Board B2 = new Board("B2");
        // B2.setNavire('D', 2, 2);
        B2.setFrappe(true, 1, 1);
        B2.print();
    }

    public static void Q3() {
        Board B1 = new Board("B1", 10, 10);
        B1.print();
        BattleShip b = new BattleShip(Orientation.NORTH);
        Submarine s = new Submarine(Orientation.WEST);
        B1.putShip(b, 1, 1);
        B1.putShip(s, 2, 1);
        B1.print();
    }

    public static void Q4() {
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
        Player p = new Player(B1, B2, shipList);
        p.putShips();
        // p.board.print();
    }

    public static void Q6() {
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
        Player p = new Player(B1, B2, shipList);
        p.putShips();
        // p.board.print();
        p.board.sendHit(0, 0);
        System.out.println(p.board.getNavire(0, 0).isSunk());
        p.board.print();
        Hit hit = p.board.sendHit(1, 0);
        System.out.println(hit);
        System.out.println(p.board.getNavire(0, 0).isSunk());
        p.board.print();
    }
 }