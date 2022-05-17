import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class gameTest {

    public static void main(String[] args) throws FileNotFoundException {

        String map[][] = new String[50][20];

        String mapOne[] = new String[20];

        Screen screen = TerminalFacade.createScreen();
        screen.startScreen();

        File file = new File("map.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            for (int i = 0; i < 20; i++) {
                mapOne[i] = sc.nextLine();
            }
        }

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = String.valueOf(mapOne[j].charAt(i));
            }
        }

        /*
         * for (int i = 0; i < 50; i++) {
         * System.out.print(map[i][1]);
         * }
         */

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 20; j++) {
                screen.putString(i + i, j, map[i][j], Terminal.Color.WHITE, Terminal.Color.BLACK);
                screen.putString(i + i + 1, j, map[i][j], Terminal.Color.WHITE, Terminal.Color.BLACK);

            }
        }
        screen.refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        screen.stopScreen();
        // System.exit(0);

    }
}
