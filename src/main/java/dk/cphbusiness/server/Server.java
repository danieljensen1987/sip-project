package dk.cphbusiness.server;

import dk.cphbusiness.entities.Student;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dk.cphbusiness.handlers.FirstRoundHandler;
import dk.cphbusiness.handlers.SubjectHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static int port = 8080;
    static String ip = "localhost";
    private final Gson gson = new Gson();

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

    class HandlerPerson implements HttpHandler {

        @Override
        public void handle(com.sun.net.httpserver.HttpExchange he) throws IOException {
            String response = "";
            int status = 200;
            String method = he.getRequestMethod().toUpperCase();
            switch (method) {

                //Used to get existing data. 
                case "GET":
                    try {
                        System.out.println("Du er her!");
                        Student s1 = new Student("2");
                        
                        List al = new ArrayList();
                        al.add(s1);
                        
                        response = gson.toJson(al);

                    } catch (NumberFormatException nfe) {
                        response = "Id is not a number";
                        status = 404;
                    }
                    break;
            }
            he.getResponseHeaders().add("Content-Type", "application/json");
            he.sendResponseHeaders(status, 0);
            try (OutputStream os = he.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
