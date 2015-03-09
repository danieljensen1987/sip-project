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

public class FirstRoundHandler implements HttpHandler {
    private static final boolean dev = false;
    Facade facade;
    private final Gson gson = new Gson();

    public FirstRoundHandler() {
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
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    if(lastIndex > 0){
                        response = facade.getfirstRoundPriorities();
                    }
                    else{
                        response = facade.getFirstRoundSubjects();
                    }
                    
                } catch (NumberFormatException nfe) {
                    response = "Id is not a number";
                    statusCode = 404;
                }
                break;

            case "POST":
                try 
                {
                    String path = he.getRequestURI().getPath();
                    int lastIndex = path.lastIndexOf("/");
                    
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonQuery = br.readLine();
                    if(lastIndex > 0){
                        facade.addTofirstRoundPriorities(path);
                    } else {
                        facade.addSubjectToFirstRound(jsonQuery);   
                    }
                                          
                } catch(IllegalArgumentException | MinimumCharacterException iae) {
                    statusCode = 200;
                    response = iae.getMessage();
                } catch (IOException e) {
                    statusCode = 500;
                    response = "Internal Server Problem";
                }        
        }

        he.getResponseHeaders().add("Content-Type", "application/json");
        he.sendResponseHeaders(statusCode, 0);
        try (OutputStream os = he.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
