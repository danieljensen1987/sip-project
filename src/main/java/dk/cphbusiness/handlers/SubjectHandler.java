package dk.cphbusiness.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dk.cphbusiness.entities.Student;
import dk.cphbusiness.entities.Subject;
import dk.cphbusiness.facade.FacadeDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                try 
                {
                    InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String jsonQuery = br.readLine();
                    if (jsonQuery.contains("<") || jsonQuery.contains(">"))
                    {
                        //Simple anti-Martin check :-)
                        throw new IllegalArgumentException("Illegal characters in input");
                    }
                    // String title, String describtion, String teachers
                    
                    Subject subject = gson.fromJson(jsonQuery, Subject.class);
                    
                    facade.addProposal(subject.getTitle(), subject.getDescription(), 
                            subject.getTeachers());
                            
                } catch(IllegalArgumentException iae) {
                    statusCode = 200;
                    response = iae.getMessage();
                } catch (IOException e) {
                    statusCode = 500;
                    response = "Internal Server Problem";
                }        
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
