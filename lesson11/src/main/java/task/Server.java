package task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This is the server of multi-user chat
 */
public class Server {

    public static final int MAX_CLIENTS = 10;
    public static final ClientHandler[] clients = new ClientHandler[MAX_CLIENTS];
    private static final Logger log = LogManager.getLogger(Server.class);

    /**
     * The entry point of Server of Multi-user Chat program.
     * It receives Client names and then begins
     * receiving all messages from them and forwarding
     * to other Clients
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        Server cs = new Server();
        cs.runServer(11111);
    }

    /**
     * This is the method which allows to run the
     * server with at most MAX_CLIENTS. It receives
     * Client names and then begins receiving all
     * messages from them and forwarding to other Clients
     *
     * @param port the port of the server
     */
    public void runServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("Server is connected to port {}", port);
            log.info("Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                for (int i = 0; i < clients.length; i++) {
                    if (clients[i] == null) {
                        ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                        clients[i] = clientHandler;
                        log.info("New client was added");
                        clientHandler.start();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
