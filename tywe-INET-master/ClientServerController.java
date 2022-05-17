import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientServerController {

    public void ServerToClientMessage(ObjectOutputStream output, Object map) throws IOException {
        output.writeObject(map);
        System.out.println("Sent new message");
        output.flush(); // send the message immediately
    }

    public void ClientToClientMessage(ClientGUI clientGUI, String serverReply) {
        clientGUI.outPutSent(serverReply);
    }

    /*
     * public void addGame(PlayerGuide playerGuide, Game game, ClientThreads thread)
     * {
     * game.addPlayer(playerGuide, thread);
     * }
     */

    /*
     * public void die(Game game, ClientThreads thread) {
     * Player player = game.getPlayerThreadMap(thread);
     * game.die(player);
     * }
     * 
     * public void playerleft(Game game, ClientThreads thread) {
     * Player player = game.getPlayerThreadMap(thread);
     * game.playerleft(player);
     * }
     */
}