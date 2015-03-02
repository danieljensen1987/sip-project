package dk.cphbusiness.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dk.cphbusiness.facade.FacadeDB;
import java.io.IOException;
import java.io.OutputStream;

public class SubjectHandler implements HttpHandler{
    private static final boolean dev = false;
    FacadeDB facade;

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
