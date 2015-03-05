package dk.cphbusiness.facade;

import com.google.gson.Gson;
import dk.cphbusiness.entities.Subject;
import java.util.ArrayList;
import java.util.List;

public class Facade implements IFacade {

    private static Facade facade = new Facade();
    private List<Subject> proposedSubjects = new ArrayList();
    private List <Subject>firstRound = new ArrayList();
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
        proposedSubjects.add(subject);
    }

    @Override
    public String getProposals() {
        return gson.toJson(proposedSubjects);
    }

    @Override
    public void addToFirstRound(String json) {
        Subject subject = gson.fromJson(json, Subject.class);
        firstRound.add(subject);
    }
    
    @Override
    public String getFirstRound() {
        return gson.toJson(firstRound);
    }
}
