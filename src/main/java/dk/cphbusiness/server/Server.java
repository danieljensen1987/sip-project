package dk.cphbusiness.server;

import com.sun.net.httpserver.HttpServer;
import dk.cphbusiness.handlers.FirstRoundHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    static int port = 9292;
    static String ip = "localhost";
    static HttpServer server;

    public void run() throws IOException {
        Server.server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/firstRound", new FirstRoundHandler());
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }
    
    public static void stop(){
        server.stop(1);
        System.out.println("Server stopped");
    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 2) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
        }
        new Server().run();
    }
}
