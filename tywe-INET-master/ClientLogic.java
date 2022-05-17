import java.io.*;
import java.net.*;

public class ClientLogic implements Runnable {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ClientGUI clientGUI;
    private Socket socket;
    private Thread thread;
    private Socket server;
    private ClientServerController CSC;
    private Game game;
    private Map map;

    public ClientLogic(String Name, int Port, ClientGUI clientGUI) throws IOException {
        server = new Socket(Name, Port);
        output = new ObjectOutputStream(server.getOutputStream());
        input = new ObjectInputStream(server.getInputStream());
        CSC = new ClientServerController();
        this.clientGUI = clientGUI;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // while(true){
            try {
                System.out.println("Starting Client run");
                // waitForServer();
                map = (Map) input.readObject();
                if(!Game.isStarted()){
                    System.out.println("Starting new Game");
                    game = new Game();
                    game.start(map,output,input);
                }else{
                    System.out.println("Updateing old Game");
                    game.updateMap(map);
                }
                System.out.println("Accepted new map"+map.toString());
                Thread.sleep(200);
            } catch (IOException | RuntimeException | InterruptedException | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        // }
    }

    private void waitForServer() throws IOException, SocketException, RuntimeException {

            Map map;
            try {
                map = (Map) input.readObject();
                if(!Game.isStarted()){
                    game = new Game();
                    game.start(map,output,input);
                }else{
                    game.updateMap(map);
                }
                System.out.println("Accepted new map"+map.toString());
                
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                System.out.println(e.toString());
            }
            
            //CSC.ClientToClientMessage(clientGUI, serverReply);

    }

}
