import java.io.*;

public class ServerGUI {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int serverPort = 9000;
    private static boolean created;
    private static ServerLogic serverLogic;

    public ServerGUI() {
        try {
            //System.out.println("Please enter the server port: ");
           // serverPort = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        while (!created) {
            try {
                ServerGUI server = new ServerGUI();
                serverLogic = new ServerLogic();
                serverLogic.waitForPlayers();
            } catch (IOException e) {
                // TODO: handle exception
                System.out.println("Couldnt create server - use different port - IO");
            } finally {
                if (serverLogic != null) {
                    serverLogic.kill();
                }
            }
        }
    }
}