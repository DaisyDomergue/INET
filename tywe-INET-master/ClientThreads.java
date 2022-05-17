import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.googlecode.lanterna.screen.Screen;

public class ClientThreads implements Runnable {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int clientNum;
    private Socket socket;
    private Thread thread;
    private Socket server;
    private Map map;
    private ClientServerController CSC;

    public ClientThreads(Socket client, Map map, int clientNum) {
        this.map = map;
        this.clientNum = clientNum;
        this.socket = client;
        CSC = new ClientServerController();
        // super.setCSC(CSC);
        // super.setGame(game);
        System.out.println(client.toString());
        try {
            // OutputStream in = new BufferedOutputStream(socket.getOutputStream());
            output = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            // input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // output = new PrintWriter(new OutputStreamWrit(socket.getOutputStream()),
            // true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Stream not set up for client");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        for (;;) {
            try {
                outputMessage(Map.getInstance());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                System.out.println("Test to client : " + Integer.toString(clientNum));
                Map.setInstance((Map) inputMessage());
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // try {
            //     // Thread.sleep(200);
            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
        }

    }

    protected void outputMessage(Map map) {
        try {
            CSC.ServerToClientMessage(output, map);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    protected Map inputMessage() {
        try {
            System.out.println("about to get map from client");
            return (Map) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Returning null");
        return null;
    }
}