import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class ServerLogic {
    private final int portValue = 9000;
    private ServerSocket socket;
    static Map game;

    public ServerLogic() {
        //this.portValue = 9000;
        try {
            socket = new ServerSocket(portValue);
            System.out.println("Server started!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void waitForPlayers() throws IOException {
        int clientNum = 1;
        while (true) {
            Socket client = socket.accept();
            System.out.println("Client arrived");
            System.out.println("Start thread for " + clientNum);
            game = Map.getInstance();
            Player p = new Player("player"+clientNum,clientNum);
            if(clientNum == 1) {
                game.addPlayer(p, "@");

            }
            else {
                game.addPlayer(p, "$");
            }
            ClientThreads task = new ClientThreads(client, game, clientNum);
            clientNum++;
            new Thread(task).start();
        }
    }

    public void kill() {
        try {
            socket.close();
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Could not Close Serversocket");
        }
    }
}