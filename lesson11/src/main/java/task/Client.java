package task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This is the Client of multi-user chat.
 * Run multiple times for multiple clients,
 * use configuration option 'Allow parallel run'
 */
public class Client {

    private static final Logger log = LogManager.getLogger(Client.class);
    private boolean closed = false;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader stdLine;

    /**
     * The entry point of Client of Multi-user Chat program.
     * It sends its name to the Server and then begins
     * sending messages to the Server and begins receiving
     * other Client messages from the Server
     *
     * @param args Array with parameters of the program
     */
    public static void main(String[] args) {
        Client cc = new Client();
        cc.runClient("localhost", 11111);
    }

    /**
     * This method allows to run the Client
     *
     * @param hostName the name of the host
     * @param port     the port of the server
     */
    public void runClient(String hostName, int port) {
        try {
            initializeResource(hostName, port);
            new Thread(new ServerReader()).start();
            while (!closed) {
                out.println(stdLine.readLine().trim());
                out.flush();
            }
            in.close();
            out.close();
            socket.close();
            log.info("Goodbye!");
        } catch (IOException e) {
            log.error("Failed to connect, please try again");
        }
    }

    /**
     * This method allows to initialize connection
     *
     * @param hostName the name of the host
     * @param port     the port of the server
     */
    public void initializeResource(String hostName, int port) throws IOException {
        socket = new Socket(hostName, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        stdLine = new BufferedReader(new InputStreamReader(System.in));
        log.info("Connection established!");
    }

    /**
     * This nested class allows to read the Server messages
     */
    public class ServerReader implements Runnable {
        @Override
        public void run() {
            String inputLine = null;
            try {
                log.info("Print client name:");
                while ((inputLine = in.readLine()) != null) {
                    log.info(inputLine);
                    if (inputLine.contains("Bye")) {
                        closed = true;
                        return;
                    }
                }
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
