package dk.cphbusiness.facade;

import com.google.gson.Gson;
import dk.cphbusiness.entities.Selected;
import dk.cphbusiness.entities.Subject;
import java.util.ArrayList;
import java.util.List;

public class Facade implements IFacade {

    private static Facade facade = new Facade();
    private List<Subject> proposedSubjects = new ArrayList();
    private List <Subject>availableCourses = new ArrayList();
    private List <Selected>priority = new ArrayList();
    private final Gson gson = new Gson();

    public static Facade getFacade(boolean b) {
        if (true) {
            facade = new Facade();
        }
        return facade;
    }

    @Override
    public void addProposal(String json) {
        Subject subject = gson.fromJson(json, Subject.class);
        System.out.println("Størrelse: " + proposedSubjects.size());
        proposedSubjects.add(subject);
    }

    @Override
    public String getProposals() {
        return gson.toJson(proposedSubjects);
    }

    @Override
    public void addToAvailableCourses(String json) {
        Subject subject = gson.fromJson(json, Subject.class);
        availableCourses.add(subject);
    }
    
    @Override
    public String getAvailableCourses() {
        return gson.toJson(availableCourses);
    }

    @Override
    public void addToFirstRound(String json) {
        Selected s1 = gson.fromJson(json, Selected.class);
        priority.add(s1);
    }

    @Override
    public String getFirstRound() {
        return gson.toJson(priority);
    }
    
    
}
