package dk.cphbusiness.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dk.cphbusiness.entities.Student;
import dk.cphbusiness.facade.FacadeDB;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SubjectHandler implements HttpHandler {

    private static final boolean dev = false;
    FacadeDB facade;
    private final Gson gson = new Gson();

    public SubjectHandler() {
        facade = FacadeDB.getFacade(false);
        if (dev) {
//            facade.createTestData();
        }
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = "";
        int statusCode = 200;
        String method = he.getRequestMethod().toUpperCase();

        switch (method) {
            case "GET":
                try {
                    System.out.println("Du er her!");
                    Student s2 = new Student("1");
                    Student s1 = new Student("2");

                    List al = new ArrayList();
                    al.add(s1);
                    al.add(s2);

                    response = gson.toJson(al);

                } catch (NumberFormatException nfe) {
                    response = "Id is not a number";
                    statusCode = 404;
                }
                break;

            case "POST":
                break;

            case "PUT":
                break;

            case "DELETE":
                break;
        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(statusCode, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
