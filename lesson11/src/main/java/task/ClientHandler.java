package task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is Client Handler which allows Server to
 * control Clients
 */
public class ClientHandler extends Thread {

    private static final Logger log = LogManager.getLogger(ClientHandler.class);
    private final Socket socket;
    private final ClientHandler[] clients;
    private PrintWriter out;

    /**
     * The constructor of ClientHandler
     *
     * @param clientSocket   the client socket
     * @param clientsThreads the array of client handlers
     */
    public ClientHandler(Socket clientSocket, ClientHandler[] clientsThreads) {
        socket = clientSocket;
        clients = clientsThreads;
    }

    @Override
    public void run() {
        ClientHandler[] threads = this.clients;
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );
            out = new PrintWriter(socket.getOutputStream(), true);

            String clientName = in.readLine();

            for (ClientHandler thread : threads) {
                if (thread != null) {
                    thread.out.println("***SERVER: " + clientName + " joined the chat!***");
                    log.info("***SERVER: {} joined the chat!***", clientName);
                }
            }

            while (true) {
                String inputLine = in.readLine();
                log.info("{}: {}", clientName, inputLine);

                if (inputLine.startsWith("quit")) {
                    break;
                }
                for (ClientHandler thread : threads) {
                    if (thread != null) {
                        thread.out.println(clientName + ": " + inputLine);
                    }
                }
            }

            for (int i = 0; i < threads.length; i++) {
                if (threads[i] != null) {
                    threads[i].out.println(clientName + " left the chat");
                    log.info("{} left the chat", clientName);
                }
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
