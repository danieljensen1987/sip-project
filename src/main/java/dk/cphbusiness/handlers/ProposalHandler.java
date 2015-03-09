package dk.cphbusiness.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dk.cphbusiness.exceptions.MinimumCharacterException;
import dk.cphbusiness.facade.Facade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ProposalHandler implements HttpHandler {

    private static final boolean dev = false;
    Facade facade;
    private final Gson gson = new Gson();

    public ProposalHandler() {
        facade = Facade.getFacade(false);
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
                    response = facade.getProposals();
                } catch (NumberFormatException nfe) {
                    response = "Id is not a number";
                    statusCode = 404;
                }
                break;

            case "POST":
                try {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonQuery = br.readLine();

                    facade.addProposal(jsonQuery);

                } catch (MinimumCharacterException mce) {
                    statusCode = 200;
                    response = mce.getMessage();
                } catch (IOException e) {
                    statusCode = 500;
                    response = "Internal Server Problem";
                }
                break;
        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(statusCode, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
