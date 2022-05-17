import java.io.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;


public class ClientGUI {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final String serverIP = "localhost";
    private final int portValue = 9000;
    private ClientLogic clientLogic;

    public ClientGUI() {
        try {
            //System.out.println("Please enter the server IP");
            //serverIP = br.readLine();
            //System.out.println("Please enter the port number for the server");
            //portValue = 9000;
            clientLogic = new ClientLogic(serverIP, portValue, this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void outPutSent(String reply) {
        System.out.println(reply);
    }

    public static void main(String[] args) {
        ClientGUI client = new ClientGUI();

        /*
         * try {
         * client.TakeInputAndAct();
         * } catch (IOException e) {
         * //TODO: handle exception
         * System.out.println("Buffer Reader does not exist");
         * }
         */

    }

    private void TakeInputAndAct() {
    }

}