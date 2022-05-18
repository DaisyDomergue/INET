import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Blob;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class Game {
    // class initializes the level/map and handles player keyboard inputs
    private static Map level;
    private static Enemy[] eList;
    private Player player;
    private static boolean started = false;
    private Screen screen;
    private ObjectOutputStream output;

    /*
     * public Game() {
     * player = new Player(1, 1);
     * 
     * }
     */
    public void updateMap(Map map){
        level = map;
        level.PrintMap(screen);

    }
    public static boolean isStarted(){
        return started;
    }
    public void startGame(Screen sc, Map map, ObjectOutputStream op, ObjectInputStream ip) throws FileNotFoundException {
        // Load level
        level = map;
        screen = sc;

        // Start the enemy threads and pass them the map to manipulate
        //System.out.println("about to print map");
        
        level.PrintMap(sc);
        //System.out.println("done to print map");

        // Keep reading input until user quits the game
        /*
         * boolean stop = false;
         * while (!stop) {
         * Key key = screen.readInput();
         * while (key == null) {
         * key = screen.readInput();
         * }
         * // Move around with arrow keys in normal map view escape closes the
         * application
         * switch (key.getKind()) {
         * case Escape:
         * stop = true;
         * break;
         * case ArrowRight:
         * if (validMove("right")) {
         * level.movePlayerRight();
         * }
         * level.PrintMap(screen);
         * break;
         * case ArrowLeft:
         * if (validMove("left")) {
         * level.movePlayerLeft();
         * }
         * level.PrintMap(screen);
         * break;
         * 
         * case ArrowDown:
         * if (validMove("down")) {
         * level.movePlayerDown();
         * }
         * level.PrintMap(screen);
         * break;
         * 
         * case ArrowUp:
         * if (validMove("up")) {
         * level.movePlayerUp();
         * }
         * level.PrintMap(screen);
         * break;
         * default:
         * break;
         * }
         */
        /*
         * for (Enemy e : eList) {
         * // after a player moves all the enemies try to attack
         * // if the player is on the same space as any of them the player will be hurt
         * e.attack();
         * }
         * // After the attacks check the player to see if the game is over
         * if (level.getPlayerHealth() <= 0) {
         * stop = true;
         * }
         * }
         */

        movementController(sc,op,ip);

        // stop all enemy threads so that final screen can be shown
        // for (Enemy e : level.geteList()) {
        //     e.interrupt();
        // }
        // // wait to guarantee that all threads have stopped before printing death screen
        // try {
        //     Thread.sleep(200);
        // } catch (InterruptedException e) {
        // }

    }

    // Checks to see if a given movement can be made by the player
    // No walking through walls and no going out of bounds
    public static boolean validMove(String move) {
        int posX = level.getPlayerX();
        int posY = level.getPlayerY();
        int maxX = level.getMaxX();
        int maxY = level.getMaxY();

        if (move.equals("right")) {
            if (posX < maxX && !level.getStringAt(posX + 1, posY).equals("#")) {
                return true;
            }
            return false;
        } else if (move.equals("left")) {
            if (posX > 0 && !level.getStringAt(posX - 1, posY).equals("#")) {
                return true;
            }
            return false;
        } else if (move.equals("down")) {
            if (posY < maxY && !level.getStringAt(posX, posY + 1).equals("#")) {
                return true;
            }
            return false;
        } else {// move.equals("up")
            if (posY > 0 && !level.getStringAt(posX, posY - 1).equals("#")) {
                return true;
            }
            return false;
        }
    }

    public static void movementController(Screen screen,ObjectOutputStream op, ObjectInputStream input) {
        //System.out.println("Starting Movement Controls");
        boolean stop = false;
        while (!stop) {
            Key key = screen.readInput();
            while (key == null) {
                key = screen.readInput();
            }

            // Move around with arrow keys in normal map view escape closes the application
            switch (key.getKind()) {
                case Escape:
                    stop = true;
                    break;
                case ArrowRight:
                    if (validMove("right")) {
                        level.movePlayerRight();
                    }
                    level.PrintMap(screen);
                    break;
                case ArrowLeft:
                    if (validMove("left")) {
                        level.movePlayerLeft();
                    }
                    level.PrintMap(screen);
                    break;

                case ArrowDown:
                    if (validMove("down")) {
                        level.movePlayerDown();
                    }
                    level.PrintMap(screen);
                    break;

                case ArrowUp:
                    if (validMove("up")) {
                        level.movePlayerUp();
                    }
                    level.PrintMap(screen);
                    break;
                default:
                    break;
            }
            try {
               // System.out.println("sending map to server");
                op.writeObject(level);
                op.flush();
               // System.out.println("Sent new message");

                Map map = (Map) input.readObject();
                level = map;
                level.PrintMap(screen);
               // System.out.println("Getting map from server");
            } catch (IOException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void start(Map map,ObjectOutputStream op, ObjectInputStream input) throws FileNotFoundException {
        started = true;
        this.output = op;
        Screen screen = TerminalFacade.createScreen();
        screen.startScreen();
        this.startGame(screen, map, output ,input);
    }

    /*
     * public void addPlayer(PlayerGuide playerGuide, ClientThreads thread) {
     * }
     * 
     * public void die(Player player) {
     * }
     * 
     * public void playerleft(Player player) {
     * }
     * 
     * public String getGoal() {
     * return null;
     * }
     * 
     * @Override
     * public void run() {
     * // TODO Auto-generated method stub
     * 
     * }
     */
}
