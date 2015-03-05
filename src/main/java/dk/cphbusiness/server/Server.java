package dk.cphbusiness.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import dk.cphbusiness.handlers.FirstRoundHandler;
import dk.cphbusiness.handlers.SubjectHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    static int port = 8080;
    static String ip = "localhost";

    public void run() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext("/data", new SubjectHandler());
        server.createContext("/addRoundOne", new FirstRoundHandler());
//        server.createContext(filesUri, new HandlerFileServer());
        server.start();
        System.out.println("Server started, listening on port: " + port);
    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 2) {
            port = Integer.parseInt(args[0]);
            ip = args[1];
        }
        new Server().run();
    }
}
