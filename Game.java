import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom:");

            // TODO use a scanner to read player name
            String name_player = sin.next();
            // TODO init boards
            Board B1, B2;
            B1 = new Board("B1", 10, 10);
            B2 = new Board("B2", 10, 10);
            Destroyer d1 = new Destroyer();
            Submarine s1 = new Submarine();
            Submarine s2 = new Submarine();
            BattleShip b1 = new BattleShip();
            Aircraft_Carrier a1 = new Aircraft_Carrier();
            Destroyer d2 = new Destroyer();
            Submarine s3 = new Submarine();
            Destroyer d3 = new Destroyer();
            BattleShip b2 = new BattleShip();
            Aircraft_Carrier a2 = new Aircraft_Carrier();
        
            List<AbstractShip> ships1 = new ArrayList<AbstractShip>();
            ships1.add(d1);
            ships1.add(s1);
            ships1.add(s2);
            ships1.add(b1);
            ships1.add(a1);
        
            List<AbstractShip> ships2 = new ArrayList<AbstractShip>();
            ships2.add(a2);
            ships2.add(s3);
            ships2.add(d3);
            ships2.add(b2);
            ships2.add(d2);
            // TODO init this.player1 & this.player2
            player1 = new Player(B1, B2, ships1);
            player2 = new Player(B2, B1, ships2);
            B1.print();
            B2.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /*
     * *** Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // TODO player1 send a hit
            System.out.println("Player 1, please enter the coordinates of your hit:");
            coords[0] = sin.next();
            coords[1] = sin.next();
            hit = player1.sendHit(coords);
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = Hit.MISS; // TODO player2 send a hit.
                    System.out.println("Player 2, please enter the coordinates of your hit:");
                    coords[0] = sin.next();
                    coords[1] = sin.next();
                    hit = player1.sendHit(coords);
                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    private void save() {
        try {
            // TODO bonus 2 : uncomment
            // if (!SAVE_FILE.exists()) {
            // SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            // }

            // TODO bonus 2 : serialize players

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
        case MISS:
            msg = hit.toString();
            break;
        case STIKE:
            msg = hit.toString();
            color = ColorUtil.Color.RED;
            break;
        default:
            msg = hit.toString() + " coulé";
            color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new BattleShip(),
                new Carrier() });
    }
}
